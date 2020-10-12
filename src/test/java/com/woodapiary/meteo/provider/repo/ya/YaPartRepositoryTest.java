/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ya;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
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
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.ya.YaPart;
import com.woodapiary.meteo.provider.repo.ya.YaPartRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit

public class YaPartRepositoryTest {

    static Logger log = LoggerFactory.getLogger(YaPartRepositoryTest.class);

    @Autowired
    private YaPartRepository repo;

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
        final YaPart ent = repo.save(createEntity());
        //System.out.println(ent.getPartId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getPartId());
    }

    @Test
    public void test02() {
        final YaPart ent1 = repo.save(createEntity());
        final YaPart ent2 = repo.findById(ent1.getPartId()).orElseThrow();
        //System.out.println(ent1.getPartId());
        assertEquals(ent1, ent2);
    }

    @After
    public void after() {
        repo.deleteAll();
    }

    YaPart createEntity() {
        final YaPart entity = new YaPart();
        entity.setConditionw("overcast");
        entity.setDaytime("d");
        entity.setFeelsLike(6);
        entity.setHumidity(79);
        entity.setIcon("ovc");
        entity.setPolar(false);
        entity.setPressureMm(742);
        entity.setPressurePa(990);
        entity.setPartName("evening");
        entity.setTempMin(7);
        entity.setTempMax(9);
        entity.setTempAvg(8);
        entity.setWindDir("nw");
        entity.setWindGust(7.3);
        entity.setWindSpeed(2.7);
        entity.setPrecMm(0.0);
        entity.setPrecPeriod(360);
        entity.setPrecProb(0);
        return entity;
    }

}
