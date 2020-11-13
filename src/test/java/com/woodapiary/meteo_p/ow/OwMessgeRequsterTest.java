/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow;

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

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.ow.dao.OwDao;
import com.woodapiary.meteo_p.ow.dto.OwMessageDto;
import com.woodapiary.meteo_p.ow.service.OwMessageRequster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwMessgeRequsterTest {
    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private OwMessageRequster requester;
    @Autowired
    private SourceDao sRepo;
    @Autowired
    private OwDao owDao;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test02() throws IOException {
        assumeTrue("request to real service", providerTestEnabled);
        final OwMessageDto result = requester.request(createSourceOw());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getDt());
    }

    @Test
    public void test04() {
        //TODO замокать
        assumeTrue("request to real service", providerTestEnabled);
        sRepo.saveSource(createSourceOw());
        requester.run();
        assertEquals(1, owDao.countMessages());
    }

    Source createSourceOw() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("openweather-moscow");
        entity.setUrl("https://api.openweathermap.org/data/2.5/onecall");
        entity.setProvider("openweathermap");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        return entity;
    }

}
