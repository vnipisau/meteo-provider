/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;
import com.woodapiary.meteo.provider.repo.ya.YaMessageRepository;
import com.woodapiary.meteo.provider.service.ProviderScheduler;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderSchedulerTest {

    @Autowired
    ProviderScheduler sheduler;
    @Autowired
    private YaDao dao;
    @Autowired
    private SourceRepository sRepo;
    @Autowired
    private YaMessageRepository mRepo;

    @Test
    public void test01() {
        assertNotNull(sheduler);
    }

    @Ignore("request to real service")
    @Test
    public void test02() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
        sRepo.save(createSource());
        sheduler.run();
        assertEquals(1, mRepo.count());
        dao.deleteAllMessages();
        sRepo.deleteAll();
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