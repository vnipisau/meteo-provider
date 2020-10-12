/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ya;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.repo.ya.YaFactRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class YaFactRepositoryTest {

    static Logger log = LoggerFactory.getLogger(YaFactRepositoryTest.class);

    @Autowired
    private YaFactRepository repo;

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
        final YaFact ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getFactId());
    }

    @Test
    public void test02() {
        final YaFact ent1 = repo.save(createEntity());
        final YaFact ent2 = repo.findById(ent1.getFactId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent1, ent2);
    }

    @Test
    public void test03() {
        repo.save(createEntity());
        final List<YaFact> res = repo.findBySource("sourceId");
        assertEquals(1, res.size());
    }

    @After
    public void after() {
        repo.deleteAll();
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
