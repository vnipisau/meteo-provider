/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.entity.ya.YaPart;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Commit
public class YaDaoTest {

    static Logger log = LoggerFactory.getLogger(YaDaoTest.class);

    @Autowired
    private YaDao dao;
    @Autowired
    private MeteoDao sRepo;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(dao).isNotNull();
    }

    @Test
    public void test01() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage ent = dao.saveMessage(createMessage(null, null), source);
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countMessages());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage mes = dao.saveMessage(createMessage(createFact(), null), source);
        assertEquals(1, dao.countFacts());
        assertNotNull(mes.getFact().getFactId());
    }

    @Test
    public void test03() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage mes = dao.saveMessage(createMessage(null, createForecast(createParts())), source);
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countForecast());
        assertNotNull(mes.getForecast().getForecastId());
        assertEquals(2, dao.countParts());
        assertNotNull(mes.getForecast().getParts().get(0));
        assertNotNull(mes.getForecast().getParts().get(0).getPartId());
        assertNotNull(mes.getForecast().getParts().get(0).getForecast());
        assertNotNull(mes.getForecast().getParts().get(0).getForecast().getForecastId());
    }

    @Test
    public void test04() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage mes = dao.saveMessage(createMessage(createFact(), createForecast(createParts())), source);
        assertNotNull(mes.getForecast().getForecastId());
        assertNotNull(mes.getFact().getFactId());
    }

    @Test
    public void test07() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage ent = dao.saveMessage(createMessage(null, null), source);
        final YaMessage ent2 = dao.findLastMessage(source.getSourceName());
        assertEquals(ent, ent2);
    }

    @After
    public void after() {

    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("yandex-moscow");
        entity.setProvider("yandex");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    YaMessage createMessage(YaFact yaFact, YaForecast yaForecast) {
        final YaMessage entity = new YaMessage();
        entity.setNowDt(LocalDateTime.parse("2019-10-04T14:23:08.537Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        entity.setFact(yaFact);
        entity.setForecast(yaForecast);
        return entity;
    }

    YaFact createFact() {
        final YaFact entity = new YaFact();
        entity.setObsTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    YaForecast createForecast(List<YaPart> list) {
        final YaForecast entity = new YaForecast();
        entity.setDate(LocalDate.parse("2019-10-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        entity.setDateTs(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570136400L * 1000), ZoneId.of("UTC")));
        entity.setParts(list);
        return entity;
    }

    YaPart createPart() {
        final YaPart entity = new YaPart();
        return entity;
    }

    List<YaPart> createParts() {
        final List<YaPart> res = new ArrayList<>();
        res.add(createPart());
        res.add(createPart());
        return res;
    }

    void clear() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

}
