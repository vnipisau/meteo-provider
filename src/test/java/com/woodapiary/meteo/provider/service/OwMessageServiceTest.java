/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;
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

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "meteo-provider.scheduling.enabled=false")
@Transactional
public class OwMessageServiceTest {

    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ow_onecall.json";
    @Autowired
    private OwMessageService requester;
    @Autowired
    private OwDao dao;
    @Autowired
    private MeteoDao sRepo;
    @Autowired
    private OwDirectoryService dir;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test02() throws IOException {
        //TODO
        assumeTrue("request to real service", providerTestEnabled);
        final OwMessageDto result = requester.request(createSource());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getDt());
    }

    @Test
    public void test03() throws IOException {
        final OwMessageDto result = requester.readFromFile(testDataPath + testDataFile);
        //System.out.println(result.toString());
        assertNotNull(result.getCurrent().getDt());
    }

    @Test
    public void test04() throws IOException {
        dao.deleteAllMessages();
        sRepo.deleteAll();
        dao.deleteWeatherConditionCodes();
        final Source source = sRepo.saveSource(createSource());
        dir.saveToDb();
        final OwMessageDto dto = requester.readFromFile(testDataPath + testDataFile);
        requester.saveToDb(dto, source);
        assertEquals(1, dao.countMessages());
        dao.deleteAllMessages();
        sRepo.deleteAll();
        dao.deleteWeatherConditionCodes();
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
