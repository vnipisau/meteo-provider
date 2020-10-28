/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.woodapiary.meteo.provider.dto.ow.OwAlertDto;
import com.woodapiary.meteo.provider.dto.ow.OwCurrentDto;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ow.OwRainDto;
import com.woodapiary.meteo.provider.entity.ow.OwAlert;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.service.OwMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwMessageDtoEntityMapperTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ow_onecall.json";
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
        final OwMessageDto dto1 = requester.readFromFile(testDataPath + testDataFile);
        dto1.setCurrent(null);
        dto1.setAlerts(null);
        dto1.setDaily(null);
        dto1.setHourly(null);
        dto1.setLat(null);
        dto1.setLon(null);
        dto1.setTimezone(null);
        dto1.setTimezoneOffset(null);
        final OwMessage entity = mapper.messageDtoToMessage(dto1);
        final OwMessageDto dto2 = mapper.messageDtoFromMessage(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void test03() throws IOException {
        final OwCurrentDto dto1 = requester.readFromFile(testDataPath + testDataFile).getCurrent();
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

    @Test
    public void test04() throws IOException {
        final List<OwAlertDto> dto1 = requester.readFromFile(testDataPath + testDataFile).getAlerts();
        final List<OwAlert> entity = mapper.alertListDtoToAlertList(dto1);
        final List<OwAlertDto> dto2 = mapper.alertListDtoFromAlertList(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

}
