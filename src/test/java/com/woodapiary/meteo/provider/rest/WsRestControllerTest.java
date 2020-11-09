/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.rest;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;

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
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dto.ws.WsGetFactsResultDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.repo.SourceRepository;
import com.woodapiary.meteo.provider.service.WsMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WsRestControllerTest {

    static Logger log = LoggerFactory.getLogger(WsRestControllerTest.class);

    @Autowired
    private WsMessageService messageService;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private WsDao dao;
    @Autowired
    private SourceRepository sRepo;

    @Before
    @Commit
    public void insert() {
        final Source source = sRepo.save(createSource());
        dao.saveMessage(createMessage(createFact()), source.getSourceName());
    }

    @Test
    public void test00() {
        assertThat(restTemplate).isNotNull();
        assertThat(messageService.getFacts("weatherstack-moscow").size() > 0).isTrue();
    }

    @Test
    public void test01() {
        final ResponseEntity<WsGetFactsResultDto> response = restTemplate.getForEntity("/api/get-ws-facts", WsGetFactsResultDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //System.out.println(response.getStatusCode());
        assertThat(response.getBody().getFacts()).isNotNull();
        assertThat(response.getBody().getFacts().size() > 0).isTrue();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("weatherstack-moscow");
        entity.setUrl("http://api.weatherstack.com/current");
        entity.setProvider("weatherstack");
        entity.setEnabled(true);
        return entity;
    }

    WsMessage createMessage(WsFact wsFact) {
        final WsMessage entity = new WsMessage();
        entity.setFact(wsFact);
        return entity;
    }

    WsFact createFact() {
        final WsFact entity = new WsFact();
        entity.setObservationTime(LocalTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    @After
    public void after() {
        dao.deleteAllMessages();
        sRepo.deleteAll();
    }

}
