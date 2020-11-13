/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo_p.util.ObjectSerializator;
import com.woodapiary.meteo_p.ya.dto.YaFactDto;
import com.woodapiary.meteo_p.ya.dto.YaForecastDto;
import com.woodapiary.meteo_p.ya.dto.YaMessageDto;
import com.woodapiary.meteo_p.ya.entities.YaFact;
import com.woodapiary.meteo_p.ya.entities.YaForecast;
import com.woodapiary.meteo_p.ya.entities.YaMessage;
import com.woodapiary.meteo_p.ya.service.YaMessageDtoEntityMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class YaMessageDtoEntityMapperTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ya_v1.json";
    @Autowired
    private YaMessageDtoEntityMapper mapper;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final YaMessageDto dto1 = new ObjectSerializator<YaMessageDto>().readJsonFromFile(testDataPath + testDataFile, YaMessageDto.class);
        dto1.setInfo(null);
        dto1.setFact(null);
        dto1.setForecast(null);
        final YaMessage entity = mapper.messageDtoToMessage(dto1);
        final YaMessageDto dto2 = mapper.messageDtoFromMessage(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.toString().length() > 0);
    }

    @Test
    public void test03() throws IOException {
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().readJsonFromFile(testDataPath + testDataFile, YaMessageDto.class);
        final YaFactDto dto1 = dto.getFact();
        final YaFact entity = mapper.factDtoToFact(dto1);
        final YaFactDto dto2 = mapper.factDtoFromFact(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void test04() throws IOException {
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().readJsonFromFile(testDataPath + testDataFile, YaMessageDto.class);
        final YaForecastDto dto1 = dto.getForecast();
        final YaForecast entity = mapper.forecastDtoToForecast(dto1);
        final YaForecastDto dto2 = mapper.forecastDtoFromForecast(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

}
