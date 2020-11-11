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

import com.woodapiary.meteo.provider.dao.SourceDao;
import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WsMessageServiceTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ws.json";
    @Autowired
    private WsMessageService service;
    @Autowired
    private WsDao dao;
    @Autowired
    private SourceDao sRepo;

    @Test
    public void test01() {
        assertNotNull(service);
    }

    @Test
    public void test03() throws IOException {
        final WsMessageDto dto = new ObjectSerializator<WsMessageDto>().readJsonFromFile(testDataPath + testDataFile, WsMessageDto.class);
        //System.out.println(result.toString());
        assertNotNull(dto.getCurrent().getObservationTime());
    }

    @Test
    public void test04() throws IOException {
        final Source source = sRepo.saveSource(createSource());
        final WsMessageDto dto = new ObjectSerializator<WsMessageDto>().readJsonFromFile(testDataPath + testDataFile, WsMessageDto.class);
        service.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
        assertEquals(1, dao.countMessages());
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("weatherstack-moscow");
        entity.setGeoname("moscow");
        entity.setUrl("http://api.weatherstack.com/current");
        entity.setProvider("weatherstack");
        entity.setEnabled(true);
        return entity;
    }

}
