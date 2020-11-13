/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya;

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
import com.woodapiary.meteo_p.ya.dao.YaDao;
import com.woodapiary.meteo_p.ya.dto.YaMessageDto;
import com.woodapiary.meteo_p.ya.service.YaMessageRequster;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaMessgeRequsterTest {
    @Value("${meteo-provider.provider.realtest.enabled}")
    private Boolean providerTestEnabled;
    @Autowired
    private YaMessageRequster requester;
    @Autowired
    private SourceDao sRepo;
    @Autowired
    private YaDao yaDao;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test02() throws IOException {
        assumeTrue("request to real service", providerTestEnabled);
        final YaMessageDto result = requester.request(createSourceYa());
        System.out.println(result.toString());
        assertNotNull(result.getNow());
    }

    @Test
    public void test03() {
        assumeTrue("request to real service", providerTestEnabled);
        sRepo.saveSource(createSourceYa());
        requester.run();
        assertEquals(1, yaDao.countMessages());
    }

    Source createSourceYa() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("yandex-moscow");
        entity.setUrl("https://api.weather.yandex.ru/v1/informers/");
        entity.setProvider("yandex");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        return entity;
    }
}
