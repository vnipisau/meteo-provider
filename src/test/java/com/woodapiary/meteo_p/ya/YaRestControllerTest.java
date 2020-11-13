/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
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
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.misc.repo.SourceRepository;
import com.woodapiary.meteo_p.ya.dao.YaDao;
import com.woodapiary.meteo_p.ya.dto.YaGetFactsResultDto;
import com.woodapiary.meteo_p.ya.entities.YaFact;
import com.woodapiary.meteo_p.ya.entities.YaMessage;
import com.woodapiary.meteo_p.ya.service.YaMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class YaRestControllerTest {

    static Logger log = LoggerFactory.getLogger(YaRestControllerTest.class);

    @Autowired
    private YaMessageService messageService;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private YaDao dao;
    @Autowired
    private SourceRepository sRepo;

    @Before
    public void insert() {
        final Source source = sRepo.save(createSource());
        dao.saveMessage(createMessage(createFact(), null), source.getProvider(), source.getGeoname());
    }

    @Test
    public void test00() {
        assertThat(restTemplate).isNotNull();
        assertThat(messageService.getFacts("yandex", "moscow").size() > 0).isTrue();
    }

    @Test
    public void test01() {
        final ResponseEntity<YaGetFactsResultDto> response = restTemplate.getForEntity("/api/get-ya-facts?location={location}", YaGetFactsResultDto.class, "moscow");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //System.out.println(response.getStatusCode());
        assertThat(response.getBody().getFacts()).isNotNull();
        assertThat(response.getBody().getFacts().size() > 0).isTrue();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("yandex-moscow");
        entity.setProvider("yandex");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    YaMessage createMessage(YaFact yaFact, Object object) {
        final YaMessage entity = new YaMessage();
        entity.setNowDt(LocalDateTime.parse("2019-10-04T14:23:08.537Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        entity.setFact(yaFact);
        return entity;
    }

    YaFact createFact() {
        final YaFact entity = new YaFact();
        entity.setObsTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    @After
    public void after() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

}
