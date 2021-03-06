/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

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
import com.woodapiary.meteo_p.ow.dao.OwDao;
import com.woodapiary.meteo_p.ow.dto.OwGetFactsResultDto;
import com.woodapiary.meteo_p.ow.entities.OwAlert;
import com.woodapiary.meteo_p.ow.entities.OwDaily;
import com.woodapiary.meteo_p.ow.entities.OwFact;
import com.woodapiary.meteo_p.ow.entities.OwHourly;
import com.woodapiary.meteo_p.ow.entities.OwMessage;
import com.woodapiary.meteo_p.ow.entities.OwWeather;
import com.woodapiary.meteo_p.ow.service.OwMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OwRestControllerTest {

    static Logger log = LoggerFactory.getLogger(OwRestControllerTest.class);

    @Autowired
    private OwMessageService messageService;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private OwDao dao;
    @Autowired
    private SourceRepository sRepo;

    @Before
    public void insert() {
        final Source source = sRepo.save(createSource());
        final List<OwWeather> ews = createWeatherList();
        dao.saveWeatherConditionCodes(ews);
        dao.saveMessage(createMessage(createFact(ews), null, null, null), source.getProvider(), source.getGeoname());
    }

    @Test
    public void test00() {
        assertThat(restTemplate).isNotNull();
        assertThat(messageService.getFacts("openweathermap", "moscow").size() == 1).isTrue();
    }

    @Test
    public void test01() {
        final ResponseEntity<OwGetFactsResultDto> response = restTemplate.getForEntity("/api/get-ow-facts?location={location}", OwGetFactsResultDto.class, "moscow");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        //System.out.println(response.getStatusCode());
        assertThat(response.getBody().getFacts()).isNotNull();
        assertThat(response.getBody().getFacts().size() == 1).isTrue();
    }

    Source createSource() {
        final Source entity = new Source();
        entity.setLat(55.75);
        entity.setLon(37.6);
        entity.setSourceName("openweathermap-moscow");
        entity.setUrl("https://api.openweathermap.org/data/2.5/onecall");
        entity.setProvider("openweathermap");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        return entity;
    }

    OwMessage createMessage(OwFact fact, List<OwDaily> daily, List<OwHourly> hourly, List<OwAlert> alerts) {
        final OwMessage entity = new OwMessage();
        entity.setFact(fact);
        entity.setDaily(daily);
        entity.setHourly(hourly);
        entity.setAlerts(alerts);
        return entity;
    }

    OwFact createFact(List<OwWeather> w) {
        final OwFact entity = new OwFact();
        entity.setWeather(w);
        entity.setDt(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    OwWeather createWeather(int i) {
        final OwWeather entity = new OwWeather();
        entity.setId(i);
        return entity;
    }

    List<OwWeather> createWeatherList() {
        final List<OwWeather> res = new ArrayList<>();
        res.add(createWeather(1));
        res.add(createWeather(2));
        return res;
    }

    @After
    public void after() {
        clear();
    }

    void clear() {
        dao.deleteAllMessages();
        dao.deleteWeatherConditionCodes();
        sRepo.deleteAll();
    }

}
