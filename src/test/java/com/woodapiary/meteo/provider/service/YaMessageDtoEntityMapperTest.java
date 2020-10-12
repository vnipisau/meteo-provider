/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dto.ya.YaFactDto;
import com.woodapiary.meteo.provider.dto.ya.YaForecastDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.service.YaMessageDtoEntityMapper;
import com.woodapiary.meteo.provider.service.YaMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaMessageDtoEntityMapperTest {

    @Autowired
    YaMessageDtoEntityMapper mapper;
    @Autowired
    YaMessageService requester;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final YaMessageDto dto1 = requester.readFromFile("src/test/data/ya_v1.json");
        final YaMessage entity = mapper.messageDtoToMessage(dto1);
        final YaMessageDto dto2 = mapper.messageDtoFromMessage(entity);
        dto1.setInfo(null);
        dto1.setFact(null);
        dto1.setForecast(null);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
    }

    @Test
    public void test03() throws IOException {
        final YaFactDto dto1 = requester.readFromFile("src/test/data/ya_v1.json").getFact();
        final YaFact entity = mapper.factDtoToFact(dto1);
        final YaFactDto dto2 = mapper.factDtoFromFact(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
    }

    @Test
    public void test04() throws IOException {
        final YaForecastDto dto1 = requester.readFromFile("src/test/data/ya_v1.json").getForecast();
        final YaForecast entity = mapper.forecastDtoToForecast(dto1);
        final YaForecastDto dto2 = mapper.forecastDtoFromForecast(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
    }

}
