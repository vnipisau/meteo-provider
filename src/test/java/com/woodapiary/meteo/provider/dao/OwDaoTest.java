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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;
import com.woodapiary.meteo.provider.repo.ow.OwFactRepository;
import com.woodapiary.meteo.provider.repo.ow.OwMessageRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class OwDaoTest {

    static Logger log = LoggerFactory.getLogger(OwDaoTest.class);

    @Autowired
    private OwDao dao;
    @Autowired
    private MeteoDao sRepo;
    @Autowired
    private OwMessageRepository mRepo;
    @Autowired
    private OwFactRepository ftRepo;

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
        final OwMessage ent = dao.saveMessage(createMessage(), source);
        //System.out.println(ent.getFactId());
        assertEquals(1, mRepo.count());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(createSource());
        final OwMessage mes = dao.saveMessage(createMessage(), source);
        final OwFact ft = dao.saveFact(mes, createFact(), createcreateWeatherList());
        //System.out.println(ent.getFactId());
        assertEquals(1, ftRepo.count());
        assertNotNull(ft.getFactId());
    }

    @Test
    public void test04() {
        final Source source = sRepo.saveSource(createSource());
        final OwMessage mes = dao.saveMessage(createMessage(), source);
        final OwFact ft = dao.saveFact(mes, createFact(), createcreateWeatherList());
        assertNotNull(ft.getFactId());
    }

    @After
    public void after() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ow-moscow");
        entity.setProvider("ow");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    OwMessage createMessage() {
        final OwMessage entity = new OwMessage();
        return entity;
    }

    OwFact createFact() {
        final OwFact entity = new OwFact();
        return entity;
    }

    OwWeather createWeather() {
        final OwWeather entity = new OwWeather();
        return entity;
    }

    List<OwWeather> createcreateWeatherList() {
        final List<OwWeather> res = new ArrayList<>();
        res.add(createWeather());
        res.add(createWeather());
        return res;
    }

}
