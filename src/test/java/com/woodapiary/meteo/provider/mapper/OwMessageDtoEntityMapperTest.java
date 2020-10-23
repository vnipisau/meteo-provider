/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

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

import com.woodapiary.meteo.provider.dto.ow.OwCurrentDto;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ow.OwRainDto;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.service.OwMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwMessageDtoEntityMapperTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    @Autowired
    private OwMessageDtoEntityMapper mapper;
    @Autowired
    private OwMessageService requester;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final OwMessageDto dto1 = requester.readFromFile(testDataPath + "/ow_onecall.json");
        final OwMessage entity = mapper.messageDtoToMessage(dto1);
        final OwMessageDto dto2 = mapper.messageDtoFromMessage(entity);
        dto1.setCurrent(null);
        dto1.setAlerts(null);
        dto1.setDaily(null);
        dto1.setHourly(null);
        dto1.setLat(null);
        dto1.setLon(null);
        dto1.setTimezone(null);
        dto1.setTimezoneOffset(null);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void test03() throws IOException {
        final OwCurrentDto dto1 = requester.readFromFile(testDataPath + "ow_onecall.json").getCurrent();
        final OwRainDto ord = new OwRainDto();
        ord.set1h(0.1);
        dto1.setRain(ord);
        final OwFact entity = mapper.factDtoToFact(dto1);
        final OwCurrentDto dto2 = mapper.factDtoFromFact(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

}
