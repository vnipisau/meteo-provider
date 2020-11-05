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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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

import com.woodapiary.meteo.provider.entity.ya.YaForecast;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class YaForecastRepositoryTest {

    static Logger log = LoggerFactory.getLogger(YaForecastRepositoryTest.class);

    @Autowired
    private YaForecastRepository repo;

    @Before
    public void before() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final YaForecast ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getForecastId());
    }

    @Test
    public void test02() {
        final YaForecast ent = repo.save(createEntity());
        final YaForecast ent2 = repo.findById(ent.getForecastId()).orElseThrow();
        //System.out.println(ent1.getFactId());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @After
    public void after() {

    }

    YaForecast createEntity() {
        final YaForecast entity = new YaForecast();
        entity.setDate(LocalDate.parse("2019-10-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        entity.setDateTs(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570136400L * 1000), ZoneId.of("UTC")));
        entity.setWeek(40);
        entity.setMoonCode(11);
        entity.setMoonText("growing-moon");
        entity.setSunrise(LocalTime.parse("06:37", DateTimeFormatter.ofPattern("HH:mm")));
        entity.setSunset(LocalTime.parse("17:58", DateTimeFormatter.ofPattern("HH:mm")));
        return entity;
    }

}
