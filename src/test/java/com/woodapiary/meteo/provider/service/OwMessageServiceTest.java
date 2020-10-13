/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;
import com.woodapiary.meteo.provider.repo.ya.YaMessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwMessageServiceTest {

    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private OwMessageService requester;
    @Autowired
    private OwDao dao;
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
        //assumeThat("request to real service", providerTestEnabled, is(true));
        final OwMessageDto result = requester.request(createSource());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getDt());
    }

    @Test
    public void test03() throws IOException {
        final OwMessageDto result = requester.readFromFile("src/test/data/ow_onecall.json");
        //System.out.println(result.toString());
        assertNotNull(result.getCurrent().getDt());
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("openweather-moscow");
        entity.setUrl("https://api.openweathermap.org/data/2.5/onecall");
        entity.setProvider("openweather");
        entity.setEnabled(true);
        return entity;
    }

}
