/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ya;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
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

import com.woodapiary.meteo.provider.entity.ya.YaFact;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class YaFactRepositoryTest {

    static Logger log = LoggerFactory.getLogger(YaFactRepositoryTest.class);

    @Autowired
    private YaFactRepository repo;

    @Before
    public void before() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final YaFact ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getFactId());
    }

    @Test
    public void test02() {
        final YaFact ent = repo.save(createEntity());
        final YaFact ent2 = repo.findById(ent.getFactId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @Test
    public void test03() {
        repo.save(createEntity());
        //FIXME
        final List<YaFact> res = repo.findBySource(null);
        assertEquals(1, res.size());
    }

    @After
    public void after() {

    }

    YaFact createEntity() {
        final YaFact entity = new YaFact();
        entity.setConditionw("overcast");
        entity.setDaytime("d");
        entity.setFeelsLike(6);
        entity.setHumidity(79);
        entity.setIcon("ovc");
        entity.setObsTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        entity.setPolar(false);
        entity.setPressureMm(742);
        entity.setPressurePa(990);
        entity.setSeason("autumn");
        entity.setTemp(9);
        entity.setWindDir("nw");
        entity.setWindGust(7.3);
        entity.setWindSpeed(2.7);
        return entity;
    }

}
