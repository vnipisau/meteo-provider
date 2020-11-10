/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WsDaoTest {

    static Logger log = LoggerFactory.getLogger(WsDaoTest.class);

    @Autowired
    private WsDao dao;
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
        final WsMessage ent = dao.saveMessage(createMessage(null), source.getProvider(), source.getGeoname());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countMessages());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(createSource());
        final WsMessage mes = dao.saveMessage(createMessage(createFact()), source.getProvider(), source.getGeoname());
        //System.out.println(ent.getFactId());
        assertEquals(1, dao.countFacts());
        assertNotNull(mes.getFact());
        assertNotNull(mes.getFact().getFactId());
    }

    @Test
    public void test07() {
        final Source source = sRepo.saveSource(createSource());
        final WsMessage ent = dao.saveMessage(createMessage(null), source.getProvider(), source.getGeoname());
        final WsMessage ent2 = dao.findLastMessage(source.getProvider(), source.getGeoname());
        assertEquals(ent, ent2);
    }

    @After
    public void after() {

    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ws-moscow");
        entity.setProvider("ws");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    WsMessage createMessage(WsFact wsFact) {
        final WsMessage entity = new WsMessage();
        entity.setFact(wsFact);
        return entity;
    }

    WsFact createFact() {
        final WsFact entity = new WsFact();
        return entity;
    }

    void clear() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

}
