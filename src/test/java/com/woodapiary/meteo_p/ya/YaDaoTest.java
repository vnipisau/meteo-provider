/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.ya.dao.YaDao;
import com.woodapiary.meteo_p.ya.entities.YaMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Commit
public class YaDaoTest {

    static Logger log = LoggerFactory.getLogger(YaDaoTest.class);

    @Autowired
    private YaDao dao;
    @Autowired
    private SourceDao sRepo;
    @Autowired
    private ApplicationContext appContext;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(dao).isNotNull();
    }

    @Test
    public void test01() {
        final Source source = sRepo.saveSource(appContext.getBean(Source.class));
        final YaMessage ent = dao.saveMessage(appContext.getBean(YaMessage.class, false, false), source.getProvider(), source.getGeoname());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countMessages());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(appContext.getBean(Source.class));
        final YaMessage mes = dao.saveMessage(appContext.getBean(YaMessage.class, true, false), source.getProvider(), source.getGeoname());
        assertEquals(1, dao.countFacts());
        assertNotNull(mes.getFact().getFactId());
    }

    @Test
    public void test03() {
        final Source source = sRepo.saveSource(appContext.getBean(Source.class));
        final YaMessage mes = dao.saveMessage(appContext.getBean(YaMessage.class, false, true), source.getProvider(), source.getGeoname());
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
        final Source source = sRepo.saveSource(appContext.getBean(Source.class));
        final YaMessage mes = dao.saveMessage(appContext.getBean(YaMessage.class, true, true), source.getProvider(), source.getGeoname());
        assertNotNull(mes.getForecast().getForecastId());
        assertNotNull(mes.getFact().getFactId());
    }

    @Test
    public void test07() {
        final Source source = sRepo.saveSource(appContext.getBean(Source.class));
        final YaMessage ent = dao.saveMessage(appContext.getBean(YaMessage.class, false, false), source.getProvider(), source.getGeoname());
        final YaMessage ent2 = dao.findLastMessage(source.getProvider(), source.getGeoname());
        assertEquals(ent, ent2);
    }

    @After
    public void after() {

    }

    void clear() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

}
