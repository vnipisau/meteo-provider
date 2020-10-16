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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Commit
public class MeteoDaoTest {

    static Logger log = LoggerFactory.getLogger(MeteoDaoTest.class);

    @Autowired
    private SourceRepository sRepo;

    @Before
    public void insert() {
        sRepo.deleteAll();
    }

    @Test
    public void test00() {
        assertThat(sRepo).isNotNull();
    }

    @Test
    public void test01() {
        final Source source = sRepo.save(createSource());
        //System.out.println(ent.getFactId());
        assertTrue(source.getSourceId() > 0);

    }

    @After
    public void after() {
        sRepo.deleteAll();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("ws-moscow");
        entity.setProvider("ws");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

}
