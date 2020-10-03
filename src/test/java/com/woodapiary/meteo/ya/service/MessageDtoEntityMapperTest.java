/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.ya.dto.FactDto;
import com.woodapiary.meteo.ya.dto.ForecastDto;
import com.woodapiary.meteo.ya.dto.MessageDto;
import com.woodapiary.meteo.ya.entity.Fact;
import com.woodapiary.meteo.ya.entity.Forecast;
import com.woodapiary.meteo.ya.entity.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageDtoEntityMapperTest {

    @Autowired
    MessageDtoEntityMapper mapper;
    @Autowired
    YaMessageService requester;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final MessageDto dto1 = requester.readFromFile("src/test/data/ya_v1.json");
        final Message entity = mapper.messageDtoToMessage(dto1);
        final MessageDto dto2 = mapper.messageDtoFromMessage(entity);
        dto1.setInfo(null);
        dto1.setFact(null);
        dto1.setForecast(null);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
    }

    @Test
    public void test03() throws IOException {
        final FactDto dto1 = requester.readFromFile("src/test/data/ya_v1.json").getFact();
        final Fact entity = mapper.factDtoToFact(dto1);
        final FactDto dto2 = mapper.factDtoFromFact(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
    }

    @Test
    public void test04() throws IOException {
        final ForecastDto dto1 = requester.readFromFile("src/test/data/ya_v1.json").getForecast();
        final Forecast entity = mapper.forecastDtoToForecast(dto1);
        final ForecastDto dto2 = mapper.forecastDtoFromForecast(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
    }

}
