/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import com.woodapiary.meteo.ya.entity.Message;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class MessageRepositoryTest {

    static Logger log = LoggerFactory.getLogger(MessageRepositoryTest.class);

    @Autowired
    private MessageRepository repo;

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
        final Message ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());
    }

    @Test
    public void test02() {
        final Message ent1 = repo.save(createEntity());
        final Message ent2 = repo.findById(ent1.getMessageId()).orElseThrow();
        System.out.println(ent1.getModified());
        System.out.println(ent2.getModified());
        assertEquals(ent1, ent2);
    }

    @After
    public void test03() {
        repo.deleteAll();
    }

    Message createEntity() {
        final Message entity = new Message();
        entity.setNowDt(LocalDateTime.parse("2019-10-04T14:23:08.537Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        return entity;
    }

}
