/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dao.MeteoDao;
import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.entity.Source;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderSchedulerTest {

    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private ProviderScheduler sheduler;
    @Autowired
    private YaDao daoYa;
    @Autowired
    private WsDao daoWs;
    @Autowired
    private OwDao daoOw;
    @Autowired
    private MeteoDao sRepo;

    @Test
    public void test01() {
        assertNotNull(sheduler);
    }

    @Test
    public void test02() {
        assumeTrue("request to real service", providerTestEnabled);
        daoYa.deleteAllMessages();
        sRepo.deleteAll();
        sRepo.saveSource(createSourceYa());
        sheduler.runYa();
        assertEquals(1, daoYa.countMessages());
        daoYa.deleteAllMessages();
        sRepo.deleteAll();
    }

    @Test
    public void test03() {
        assumeTrue("request to real service", providerTestEnabled);
        daoWs.deleteAllMessages();
        sRepo.deleteAll();
        sRepo.saveSource(createSourceWs());
        sheduler.runWs();
        assertEquals(1, daoWs.countMessages());
        daoWs.deleteAllMessages();
        sRepo.deleteAll();
    }

    @Test
    public void test04() {
        assumeTrue("request to real service", providerTestEnabled);
        daoOw.deleteAllMessages();
        sRepo.deleteAll();
        sRepo.saveSource(createSourceOw());
        sheduler.runOw();
        assertEquals(1, daoOw.countMessages());
        daoOw.deleteAllMessages();
        sRepo.deleteAll();
    }

    Source createSourceYa() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("yandex-moscow");
        entity.setUrl("https://api.weather.yandex.ru/v1/informers/");
        entity.setProvider("yandex");
        entity.setEnabled(true);
        return entity;
    }

    Source createSourceWs() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("weatherstack-moscow");
        entity.setUrl("http://api.weatherstack.com/current");
        entity.setProvider("weatherstack");
        entity.setEnabled(true);
        return entity;
    }

    Source createSourceOw() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("openweathermap-moscoapw");
        entity.setUrl("https://api.openweathermap.org/data/2.5/onecall");
        entity.setProvider("openweathermap");
        entity.setEnabled(true);
        return entity;
    }

}
