/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;
import com.woodapiary.meteo.provider.repo.ya.YaMessageRepository;
import com.woodapiary.meteo.provider.service.YaMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaMessageServiceTest {

    @Autowired
    YaMessageService requester;
    @Autowired
    private YaDao dao;
    @Autowired
    private SourceRepository sRepo;
    @Autowired
    private YaMessageRepository mRepo;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Ignore("request to real service")
    @Test
    public void test02() throws IOException {
        final YaMessageDto result = requester.request(createSource());
        System.out.println(result.toString());
        assertNotNull(result.getNow());
    }

    @Test
    public void test03() throws IOException {
        final YaMessageDto result = requester.readFromFile("src/test/data/ya_v1.json");
        //System.out.println(result.toString());
        assertNotNull(result.getNow());
    }

    //@Ignore
    @Test
    public void test04() throws IOException {
        dao.deleteAllMessages();
        sRepo.deleteAll();
        final Source source = sRepo.save(createSource());
        final YaMessageDto dto = requester.readFromFile("src/test/data/ya_v1.json");
        requester.saveToDb(dto, source);
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