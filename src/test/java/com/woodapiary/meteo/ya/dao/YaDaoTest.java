/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.dao;

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

import com.woodapiary.meteo.ya.entity.Fact;
import com.woodapiary.meteo.ya.entity.Forecast;
import com.woodapiary.meteo.ya.entity.Message;
import com.woodapiary.meteo.ya.entity.Part;
import com.woodapiary.meteo.ya.entity.Source;
import com.woodapiary.meteo.ya.repo.FactRepository;
import com.woodapiary.meteo.ya.repo.ForecastRepository;
import com.woodapiary.meteo.ya.repo.MessageRepository;
import com.woodapiary.meteo.ya.repo.PartRepository;
import com.woodapiary.meteo.ya.repo.SourceRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class YaDaoTest {

    static Logger log = LoggerFactory.getLogger(YaDaoTest.class);

    @Autowired
    private YaDao dao;
    @Autowired
    private SourceRepository sRepo;
    @Autowired
    private MessageRepository mRepo;
    @Autowired
    private FactRepository ftRepo;
    @Autowired
    private ForecastRepository fcRepo;
    @Autowired
    private PartRepository pRepo;

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
        final Source source = sRepo.save(createSource());
        final Message ent = dao.saveMessage(createMessage(), source);
        //System.out.println(ent.getFactId());
        assertEquals(1, mRepo.count());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.save(createSource());
        final Message mes = dao.saveMessage(createMessage(), source);
        final Fact ft = dao.saveFact(mes, createFact());
        //System.out.println(ent.getFactId());
        assertEquals(1, ftRepo.count());
        assertNotNull(ft.getFactId());
    }

    @Test
    public void test03() {
        final Source source = sRepo.save(createSource());
        final Message mes = dao.saveMessage(createMessage(), source);
        final Forecast fc = dao.saveForecast(mes, createForecast(), createParts());
        //System.out.println(ent.getFactId());
        assertEquals(1, fcRepo.count());
        assertNotNull(fc.getForecastId());
        assertEquals(2, pRepo.count());
        assertNotNull(fc.getParts().get(0));
        assertNotNull(fc.getParts().get(0).getPartId());
        assertNotNull(fc.getParts().get(0).getForecast().getForecastId());
    }

    @Test
    public void test04() {
        final Source source = sRepo.save(createSource());
        final Message mes = dao.saveMessage(createMessage(), source);
        final Fact ft = dao.saveFact(mes, createFact());
        final Forecast fc = dao.saveForecast(mes, createForecast(), createParts());
        assertNotNull(fc.getForecastId());
        assertNotNull(ft.getFactId());
    }

    @After
    public void test10() {
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

    Message createMessage() {
        final Message entity = new Message();
        entity.setNowDt(LocalDateTime.parse("2019-10-04T14:23:08.537Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        return entity;
    }

    Fact createFact() {
        final Fact entity = new Fact();
        entity.setObsTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    Forecast createForecast() {
        final Forecast entity = new Forecast();
        entity.setDate(LocalDate.parse("2019-10-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        entity.setDateTs(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570136400L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    Part createPart() {
        final Part entity = new Part();
        return entity;
    }

    List<Part> createParts() {
        final List<Part> res = new ArrayList<>();
        res.add(createPart());
        res.add(createPart());
        return res;
    }

}
