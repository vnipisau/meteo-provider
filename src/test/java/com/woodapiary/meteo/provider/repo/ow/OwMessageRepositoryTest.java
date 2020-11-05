/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

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

import com.woodapiary.meteo.provider.entity.ow.OwMessage;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OwMessageRepositoryTest {

    static Logger log = LoggerFactory.getLogger(OwMessageRepositoryTest.class);

    @Autowired
    private OwMessageRepository repo;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final OwMessage ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());
        final OwMessage ent2 = repo.findById(ent.getMessageId()).orElseThrow();
        //System.out.println(ent1.getModified());
        //System.out.println(ent2.getModified());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @After
    public void after() {

    }

    OwMessage createEntity() {
        final OwMessage entity = new OwMessage();
        return entity;
    }

}
