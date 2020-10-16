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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.repo.ws.WsFactRepository;
import com.woodapiary.meteo.provider.repo.ws.WsMessageRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class WsDaoTest {

    static Logger log = LoggerFactory.getLogger(WsDaoTest.class);

    @Autowired
    private WsDao dao;
    @Autowired
    private MeteoDao sRepo;
    @Autowired
    private WsMessageRepository mRepo;
    @Autowired
    private WsFactRepository ftRepo;

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
        final WsMessage ent = dao.saveMessage(createMessage(), source);
        //System.out.println(ent.getFactId());
        assertEquals(1, mRepo.count());
        assertNotNull(ent.getMessageId());
        assertEquals(source.getSourceId(), ent.getSource().getSourceId());
    }

    @Test
    public void test02() {
        final Source source = sRepo.saveSource(createSource());
        final WsMessage mes = dao.saveMessage(createMessage(), source);
        final WsFact ft = dao.saveFact(mes, createFact());
        //System.out.println(ent.getFactId());
        assertEquals(1, ftRepo.count());
        assertNotNull(ft.getFactId());
    }

    @Test
    public void test04() {
        final Source source = sRepo.saveSource(createSource());
        final WsMessage mes = dao.saveMessage(createMessage(), source);
        final WsFact ft = dao.saveFact(mes, createFact());
        assertNotNull(ft.getFactId());
    }

    @After
    public void after() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ws-moscow");
        entity.setProvider("ws");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    WsMessage createMessage() {
        final WsMessage entity = new WsMessage();
        return entity;
    }

    WsFact createFact() {
        final WsFact entity = new WsFact();
        return entity;
    }

}
