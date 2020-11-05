/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo;

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

import com.woodapiary.meteo.provider.entity.Source;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional

public class SourceRepositoryTest {

    static Logger log = LoggerFactory.getLogger(SourceRepositoryTest.class);

    @Autowired
    private SourceRepository repo;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final Source ent = repo.save(createEntity());
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getSourceId());
    }

    @Test
    public void test02() {
        final Source ent = repo.save(createEntity());
        final Source ent2 = repo.findById(ent.getSourceId()).orElseThrow();
        //System.out.println(ent1);
        assertEquals(1, repo.count());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @After
    public void after() {

    }

    Source createEntity() {
        final Source entity = new Source();
        entity.setProvider("yandex");
        entity.setGeoname("geoname");
        entity.setGeonameId(0);
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("yandex-moscow");
        entity.setUrl("https://api.weather.yandex.ru/v1/informers/");
        entity.setEnabled(true);
        entity.setApiVersion("v1");
        return entity;
    }

}
