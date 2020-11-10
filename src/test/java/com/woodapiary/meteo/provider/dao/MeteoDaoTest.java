/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

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
//@Commit
public class MeteoDaoTest {

    static Logger log = LoggerFactory.getLogger(MeteoDaoTest.class);

    @Autowired
    private MeteoDao sRepo;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(sRepo).isNotNull();
    }

    @Test
    public void test01() {
        final Source source = sRepo.saveSource(createSource());
        //System.out.println(ent.getFactId());
        assertTrue(source.getSourceId() > 0);

    }

    @After
    public void after() {

    }

    void clear() {
        sRepo.deleteAll();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ws-moscow");
        entity.setProvider("ws");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

}
