/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woodapiary.meteo.provider.dao.MeteoDao;
import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class YaMessageServiceTest {

    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ya_v1.json";
    @Autowired
    private YaMessageService service;
    @Autowired
    private YaDao dao;
    @Autowired
    private MeteoDao sRepo;

    @Test
    public void test01() {
        assertNotNull(service);
    }

    @Test
    public void test03() throws IOException {
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().readJsonFromFile(testDataPath + testDataFile, YaMessageDto.class);
        //System.out.println(result.toString());
        assertNotNull(dto.getNow());
    }

    @Test
    public void test04() throws IOException {
        final Source source = sRepo.saveSource(createSource());
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().readJsonFromFile(testDataPath + testDataFile, YaMessageDto.class);
        service.saveMessageToDb(dto, source.getSourceName());
        assertEquals(1, dao.countMessages());
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("yandex-moscow");
        entity.setUrl("https://api.weather.yandex.ru/v1/informers/");
        entity.setProvider("yandex");
        entity.setEnabled(true);
        return entity;
    }

}
