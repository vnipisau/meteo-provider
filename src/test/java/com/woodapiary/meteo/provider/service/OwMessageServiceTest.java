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
import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OwMessageServiceTest {

    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ow_onecall.json";
    @Autowired
    private OwMessageService service;
    @Autowired
    private OwDao dao;
    @Autowired
    private MeteoDao sRepo;
    @Autowired
    private OwDirectoryService dir;

    @Test
    public void test01() {
        assertNotNull(service);
    }

    @Test
    public void test03() throws IOException {
        final OwMessageDto dto1 = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
        //System.out.println(result.toString());
        assertNotNull(dto1.getCurrent().getDt());
    }

    @Test
    public void test04() throws IOException {
        final Source source = sRepo.saveSource(createSource());
        dir.saveWeatherToDb();
        final OwMessageDto dto1 = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
        service.saveMessageToDb(dto1, source.getSourceName());
        assertEquals(1, dao.countMessages());
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("openweather-moscow");
        entity.setUrl("https://api.openweathermap.org/data/2.5/onecall");
        entity.setProvider("openweathermap");
        entity.setEnabled(true);
        return entity;
    }

}
