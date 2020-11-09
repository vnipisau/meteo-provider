/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assume.assumeTrue;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequsterServiceTest {
    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private RequsterService requester;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test02() throws IOException {
        assumeTrue("request to real service", providerTestEnabled);
        final OwMessageDto result = requester.requestOw(createSourceOw());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getDt());
    }

    @Test
    public void test03() throws IOException {
        assumeTrue("request to real service", providerTestEnabled);
        final WsMessageDto result = requester.requestWs(createSourceWs());
        System.out.println(result.toString());
        assertNotNull(result.getCurrent().getObservationTime());
    }

    @Test
    public void test04() throws IOException {
        assumeTrue("request to real service", providerTestEnabled);
        final YaMessageDto result = requester.requestYa(createSourceYa());
        System.out.println(result.toString());
        assertNotNull(result.getNow());
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
        entity.setSourceName("openweather-moscow");
        entity.setUrl("https://api.openweathermap.org/data/2.5/onecall");
        entity.setProvider("openweathermap");
        entity.setEnabled(true);
        return entity;
    }

}
