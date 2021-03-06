/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
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

import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.misc.repo.SourceRepository;
import com.woodapiary.meteo_p.ws.entities.WsMessage;
import com.woodapiary.meteo_p.ws.repo.WsMessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WsMessageRepositoryTest {

    static Logger log = LoggerFactory.getLogger(WsMessageRepositoryTest.class);

    @Autowired
    private WsMessageRepository repo;
    @Autowired
    private SourceRepository repoS;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final WsMessage ent = repo.save(createEntity(null));
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());
        final WsMessage ent2 = repo.findById(ent.getMessageId()).orElseThrow();
        //System.out.println(ent1.getModified());
        //System.out.println(ent2.getModified());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @Test
    public void test03() {
        final Source src = repoS.save(createSource());
        final WsMessage ent = repo.save(createEntity(src));
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());
        final WsMessage ent2 = repo.findLastMessage(src.getSourceId());
        //System.out.println(ent2);
        assertTrue(ent.equals(ent2));
    }

    @After
    public void after() {

    }

    WsMessage createEntity(Source src) {
        final WsMessage entity = new WsMessage();
        entity.setSource(src);
        return entity;
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ws-moscow");
        entity.setProvider("ws");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

}
