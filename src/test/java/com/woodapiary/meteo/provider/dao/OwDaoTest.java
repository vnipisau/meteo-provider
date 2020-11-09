/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
import com.woodapiary.meteo.provider.entity.ow.OwAlert;
import com.woodapiary.meteo.provider.entity.ow.OwDaily;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwHourly;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
//@Commit
public class OwDaoTest {

    static Logger log = LoggerFactory.getLogger(OwDaoTest.class);

    @Autowired
    private OwDao dao;
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
        final OwMessage ent = dao.saveMessage(createMessage(null, null, null, null), source.getSourceName());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countMessages());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(createSource());
        final List<OwWeather> ews = createWeatherList();
        dao.saveWeatherConditionCodes(ews);
        final OwMessage mes = dao.saveMessage(createMessage(createFact(ews), null, null, null), source.getSourceName());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countFacts());
        assertNotNull(mes.getFact().getFactId());
    }

    @Test
    //@Commit
    public void test03() {
        final Source source = sRepo.saveSource(createSource());
        final OwMessage mes = dao.saveMessage(createMessage(null, null, null, createAlertList()), source.getSourceName());
        assertEquals(2, dao.countAlerts());
        assertNotNull(mes.getAlerts().get(0).getAlertId());
    }

    @Test
    public void test05() {
        final Source source = sRepo.saveSource(createSource());
        final List<OwWeather> ews = createWeatherList();
        dao.saveWeatherConditionCodes(ews);
        final OwMessage mes = dao.saveMessage(createMessage(null, List.of(createDaily(ews)), null, null), source.getSourceName());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countDaily());
        assertNotNull(mes.getDaily().get(0).getDailyId());
    }

    @Test
    public void test06() {
        final Source source = sRepo.saveSource(createSource());
        final List<OwWeather> ews = createWeatherList();
        dao.saveWeatherConditionCodes(ews);
        final OwMessage mes = dao.saveMessage(createMessage(null, null, List.of(createHourly(ews)), null), source.getSourceName());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countHourly());
        assertNotNull(mes.getHourly().get(0).getHourlyId());
    }

    @Test
    public void test07() {
        final Source source = sRepo.saveSource(createSource());
        final OwMessage ent = dao.saveMessage(createMessage(null, null, null, null), source.getSourceName());
        final OwMessage ent2 = dao.findLastMessage(source.getSourceName());
        assertEquals(ent, ent2);
    }

    @After
    public void after() {

    }

    void clear() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
        dao.deleteWeatherConditionCodes();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ow-moscow");
        entity.setProvider("ow");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    OwMessage createMessage(OwFact fact, List<OwDaily> daily, List<OwHourly> hourly, List<OwAlert> alerts) {
        final OwMessage entity = new OwMessage();
        entity.setFact(fact);
        entity.setDaily(daily);
        entity.setHourly(hourly);
        entity.setAlerts(alerts);
        return entity;
    }

    OwFact createFact(List<OwWeather> w) {
        final OwFact entity = new OwFact();
        entity.setWeather(w);
        return entity;
    }

    OwDaily createDaily(List<OwWeather> w) {
        final OwDaily entity = new OwDaily();
        entity.setWeather(w);
        return entity;
    }

    OwHourly createHourly(List<OwWeather> w) {
        final OwHourly entity = new OwHourly();
        entity.setWeather(w);
        return entity;
    }

    OwWeather createWeather(int i) {
        final OwWeather entity = new OwWeather();
        entity.setId(i);
        return entity;
    }

    List<OwWeather> createWeatherList() {
        final List<OwWeather> res = new ArrayList<>();
        res.add(createWeather(1));
        res.add(createWeather(2));
        return res;
    }

    OwAlert createAlert() {
        final OwAlert entity = new OwAlert();
        return entity;
    }

    List<OwAlert> createAlertList() {
        final List<OwAlert> res = new ArrayList<>();
        res.add(createAlert());
        res.add(createAlert());
        return res;
    }

}
