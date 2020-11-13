/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws;

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
import com.woodapiary.meteo_p.ws.dao.WsDao;
import com.woodapiary.meteo_p.ws.dto.WsMessageDto;
import com.woodapiary.meteo_p.ws.service.WsMessageRequster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsMessageRequsterTest {
    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private WsMessageRequster requester;
    @Autowired
    private SourceDao sRepo;
    @Autowired
    private WsDao wsDao;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test0() throws IOException {
        assumeTrue("request to real service", providerTestEnabled);
        final WsMessageDto result = requester.request(createSourceWs());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getObservationTime());
    }

    @Test
    public void test03() {
        assumeTrue("request to real service", providerTestEnabled);
        sRepo.saveSource(createSourceWs());
        requester.run();
        assertEquals(1, wsDao.countMessages());
    }

    Source createSourceWs() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("weatherstack-moscow");
        entity.setUrl("http://api.weatherstack.com/current");
        entity.setProvider("weatherstack");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        return entity;
    }

}
