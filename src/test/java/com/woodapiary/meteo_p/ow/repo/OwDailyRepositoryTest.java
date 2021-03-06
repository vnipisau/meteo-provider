/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

import com.woodapiary.meteo_p.ow.entities.OwDaily;
import com.woodapiary.meteo_p.ow.repo.OwDailyRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OwDailyRepositoryTest {

    static Logger log = LoggerFactory.getLogger(OwDailyRepositoryTest.class);

    @Autowired
    private OwDailyRepository repo;

    @Before
    public void before() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final OwDaily ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getDailyId());
        final OwDaily ent2 = repo.findById(ent.getDailyId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @After
    public void after() {

    }

    OwDaily createEntity() {
        final OwDaily entity = new OwDaily();
        entity.setDayFeelsLike(6.0);
        entity.setHumidity(79);
        entity.setDt(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        entity.setPressure(990.0);
        entity.setMinTemp(10.0);
        entity.setWindGust(1.0);
        entity.setWindDeg(100);
        entity.setWindSpeed(27.0);
        return entity;
    }

}
