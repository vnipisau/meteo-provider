/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

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

import com.woodapiary.meteo_p.ow.entities.OwAlert;
import com.woodapiary.meteo_p.ow.repo.OwAlertRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OwAlertRepositoryTest {

    static Logger log = LoggerFactory.getLogger(OwAlertRepositoryTest.class);

    @Autowired
    private OwAlertRepository repo;

    @Before
    public void before() {

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
        final OwAlert ent2 = repo.findById(ent.getAlertId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @After
    public void after() {

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
