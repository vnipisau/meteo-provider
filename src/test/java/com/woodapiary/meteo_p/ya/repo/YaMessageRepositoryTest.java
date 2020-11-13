/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
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

import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.misc.repo.SourceRepository;
import com.woodapiary.meteo_p.ya.entities.YaMessage;
import com.woodapiary.meteo_p.ya.repo.YaMessageRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class YaMessageRepositoryTest {

    static Logger log = LoggerFactory.getLogger(YaMessageRepositoryTest.class);

    @Autowired
    private YaMessageRepository repo;
    @Autowired
    private SourceRepository repoS;

    @Before
    public void insert() {

    }

    @Test
    public void test00() {
        assertThat(repo).isNotNull();
    }

    @Test
    public void test01() {
        final YaMessage ent = repo.save(createEntity(null));
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());

    }

    @Test
    public void test02() {
        final YaMessage ent = repo.save(createEntity(null));
        final YaMessage ent2 = repo.findById(ent.getMessageId()).orElseThrow();
        //System.out.println(ent1.getModified());
        //System.out.println(ent2.getModified());
        assertEquals(ent, ent2);
        assertEquals(ent.hashCode(), ent2.hashCode());
        assertTrue(ent.equals(ent2));
        assertTrue(ent.toString().length() > 0);
    }

    @Test
    public void test03() {
        final Source src = repoS.save(createSource());
        final YaMessage ent = repo.save(createEntity(src));
        //System.out.println(ent.getFactId());
        assertEquals(1, repo.count());
        assertNotNull(ent.getMessageId());
        final YaMessage ent2 = repo.findLastMessage(src.getSourceId());
        //System.out.println(ent2);
        assertTrue(ent.equals(ent2));
    }

    @After
    public void after() {

    }

    YaMessage createEntity(Source src) {
        final YaMessage entity = new YaMessage();
        entity.setNowDt(LocalDateTime.parse("2019-10-04T14:23:08.537Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        entity.setSource(src);
        return entity;
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("yandex-moscow");
        entity.setProvider("yandex");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

}
