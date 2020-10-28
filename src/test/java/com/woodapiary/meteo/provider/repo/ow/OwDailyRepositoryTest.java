/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.ow.OwDaily;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class OwDailyRepositoryTest {

    static Logger log = LoggerFactory.getLogger(OwDailyRepositoryTest.class);

    @Autowired
    private OwDailyRepository repo;

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
        final OwDaily ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getDailyId());
    }

    @Test
    public void test02() {
        final OwDaily ent1 = repo.save(createEntity());
        final OwDaily ent2 = repo.findById(ent1.getDailyId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent1, ent2);
    }

    @After
    public void after() {
        repo.deleteAll();
    }

    OwDaily createEntity() {
        final OwDaily entity = new OwDaily();
        entity.setDayFeelsLike(6.0);
        entity.setHumidity(79);
        entity.setDt(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        entity.setPressure(990);
        entity.setMinTemp(10.0);
        entity.setWindGust(1.0);
        entity.setWindDeg(100);
        entity.setWindSpeed(27.0);
        return entity;
    }

}
