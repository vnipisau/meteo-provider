/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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

import com.woodapiary.meteo.provider.entity.ow.OwAlert;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class OwAlertRepositoryTest {

    static Logger log = LoggerFactory.getLogger(OwAlertRepositoryTest.class);

    @Autowired
    private OwAlertRepository repo;

    @Before
    public void before() {
        repo.deleteAll();
    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final OwAlert ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getAlertId());
    }

    @Test
    public void test02() {
        final OwAlert ent1 = repo.save(createEntity());
        final OwAlert ent2 = repo.findById(ent1.getAlertId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent1, ent2);
    }

    @After
    public void after() {
        repo.deleteAll();
    }

    OwAlert createEntity() {
        final OwAlert entity = new OwAlert();
        entity.setStart(LocalDateTime.now());
        entity.setEnd(LocalDateTime.now());
        entity.setEvent("event");
        entity.setSenderName("senderName");
        entity.setDescription("description");
        return entity;
    }

}
