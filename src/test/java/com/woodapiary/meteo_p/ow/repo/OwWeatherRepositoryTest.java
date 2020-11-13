/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.repo;

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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woodapiary.meteo_p.ow.entities.OwWeather;
import com.woodapiary.meteo_p.ow.repo.OwWeatherRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest alt variant
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class OwWeatherRepositoryTest {

    static Logger log = LoggerFactory.getLogger(OwWeatherRepositoryTest.class);

    @Autowired
    private OwWeatherRepository repo;

    @Before
    public void before() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final OwWeather ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getId());
        final OwWeather ent2 = repo.findById(ent.getId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @After
    public void after() {

    }

    OwWeather createEntity() {
        final OwWeather entity = new OwWeather();
        entity.setId(1);
        entity.setIcon("icon");
        return entity;
    }

}
