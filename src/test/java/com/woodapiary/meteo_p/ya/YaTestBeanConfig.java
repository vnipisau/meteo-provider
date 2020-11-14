package com.woodapiary.meteo_p.ya;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.ya.entities.YaFact;
import com.woodapiary.meteo_p.ya.entities.YaForecast;
import com.woodapiary.meteo_p.ya.entities.YaMessage;
import com.woodapiary.meteo_p.ya.entities.YaPart;

@Configuration
public class YaTestBeanConfig {

    @Bean
    @Scope("prototype")
    Source createSource() {
        final Source entity = new Source();
        entity.setSourceName("yandex-moscow");
        entity.setProvider("yandex");
        entity.setGeoname("moscow");
        entity.setEnabled(true);
        entity.setUrl("none");
        return entity;
    }

    @Bean
    @Scope("prototype")
    YaMessage createMessage(boolean a, boolean b) {
        final YaMessage entity = new YaMessage();
        entity.setNowDt(LocalDateTime.parse("2019-10-04T14:23:08.537Z", DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")));
        if (a) {
            entity.setFact(createFact());
        }
        if (b) {
            entity.setForecast(createForecast());
        }
        return entity;
    }

    @Bean
    @Scope("prototype")
    YaFact createFact() {
        final YaFact entity = new YaFact();
        entity.setObsTime(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570197600L * 1000), ZoneId.of("UTC")));
        return entity;
    }

    @Bean
    @Scope("prototype")
    YaForecast createForecast() {
        final YaForecast entity = new YaForecast();
        entity.setDate(LocalDate.parse("2019-10-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        entity.setDateTs(LocalDateTime.ofInstant(Instant.ofEpochMilli(1570136400L * 1000), ZoneId.of("UTC")));
        entity.setParts(new ArrayList<>());
        entity.addPart(createPart());
        entity.addPart(createPart());
        return entity;
    }

    @Bean
    @Scope("prototype")
    YaPart createPart() {
        final YaPart entity = new YaPart();
        return entity;
    }

}
