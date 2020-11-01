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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.entity.ya.YaPart;
import com.woodapiary.meteo.provider.repo.ya.YaFactRepository;
import com.woodapiary.meteo.provider.repo.ya.YaForecastRepository;
import com.woodapiary.meteo.provider.repo.ya.YaMessageRepository;
import com.woodapiary.meteo.provider.repo.ya.YaPartRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class YaDaoTest {

    static Logger log = LoggerFactory.getLogger(YaDaoTest.class);

    @Autowired
    private YaDao dao;
    @Autowired
    private MeteoDao sRepo;
    @Autowired
    private YaMessageRepository mRepo;
    @Autowired
    private YaFactRepository ftRepo;
    @Autowired
    private YaForecastRepository fcRepo;
    @Autowired
    private YaPartRepository pRepo;

    @Before
    public void insert() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
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
        assertEquals(1, mRepo.count());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage mes = dao.saveMessage(createMessage(createFact(), null), source);
        assertEquals(1, ftRepo.count());
        assertNotNull(mes.getMfact().getFactId());
    }

    @Test
    public void test03() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage mes = dao.saveMessage(createMessage(null, createForecast(createParts())), source);
        //System.out.println(ent.getFactId());
        assertEquals(1, fcRepo.count());
        assertNotNull(mes.getMforecast().getForecastId());
        assertEquals(2, pRepo.count());
        assertNotNull(mes.getMforecast().getParts().get(0));
        assertNotNull(mes.getMforecast().getParts().get(0).getPartId());
        assertNotNull(mes.getMforecast().getParts().get(0).getForecast());
        assertNotNull(mes.getMforecast().getParts().get(0).getForecast().getForecastId());
    }

    @Test
    public void test04() {
        final Source source = sRepo.saveSource(createSource());
        final YaMessage mes = dao.saveMessage(createMessage(createFact(), createForecast(createParts())), source);
        assertNotNull(mes.getMforecast().getForecastId());
        assertNotNull(mes.getMfact().getFactId());
    }

    @After
    public void after() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
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
        entity.setMfact(yaFact);
        entity.setMforecast(yaForecast);
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

}
