/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ws;

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

import com.woodapiary.meteo.provider.entity.ws.WsMessage;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class WsMessageRepositoryTest {

    static Logger log = LoggerFactory.getLogger(WsMessageRepositoryTest.class);

    @Autowired
    private WsMessageRepository repo;

    @Before
    public void insert() {
        repo.deleteAll();
    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final WsMessage ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());
    }

    @Test
    public void test02() {
        final WsMessage ent1 = repo.save(createEntity());
        final WsMessage ent2 = repo.findById(ent1.getMessageId()).orElseThrow();
        //System.out.println(ent1.getModified());
        //System.out.println(ent2.getModified());
        assertEquals(ent1, ent2);
    }

    @After
    public void after() {
        repo.deleteAll();
    }

    WsMessage createEntity() {
        final WsMessage entity = new WsMessage();
        return entity;
    }

}
