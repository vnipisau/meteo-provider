/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ws;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

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

import com.woodapiary.meteo.provider.entity.ws.WsFact;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WsFactRepositoryTest {

    static Logger log = LoggerFactory.getLogger(WsFactRepositoryTest.class);

    @Autowired
    private WsFactRepository repo;

    @Before
    public void before() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final WsFact ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getFactId());
        final WsFact ent2 = repo.findById(ent.getFactId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @Test
    public void test02() {

    }

    @Test
    public void test03() {
        repo.save(createEntity());
        final List<WsFact> res = repo.findBySource("sourceId");
        assertEquals(1, res.size());
    }

    @After
    public void after() {

    }

    WsFact createEntity() {
        final WsFact entity = new WsFact();
        entity.setFeelslike(6);
        entity.setHumidity(79);
        entity.setWeatherIcons("ovc");
        entity.setObservationTime(LocalTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        entity.setPressure(990);
        entity.setTemperature(10);
        entity.setWindDir("nw");
        entity.setWindDegree(100);
        entity.setWindSpeed(27);
        entity.setPrecip(0.1);
        return entity;
    }

}
