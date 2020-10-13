/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;
import com.woodapiary.meteo.provider.repo.ya.YaMessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsMessageServiceTest {

    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private WsMessageService requester;
    @Autowired
    private WsDao dao;
    @Autowired
    private SourceRepository sRepo;
    @Autowired
    private YaMessageRepository mRepo;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test02() throws IOException {
        assumeThat("request to real service", providerTestEnabled, is(true));
        final WsMessageDto result = requester.request(createSource());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getObservationTime());
    }

    @Test
    public void test03() throws IOException {
        final WsMessageDto result = requester.readFromFile("src/test/data/ws.json");
        //System.out.println(result.toString());
        assertNotNull(result.getCurrent().getObservationTime());
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("weatherstack-moscow");
        entity.setUrl("http://api.weatherstack.com/current");
        entity.setProvider("weatherstack");
        entity.setEnabled(true);
        return entity;
    }

}
