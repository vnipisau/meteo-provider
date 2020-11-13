/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow;

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

import com.woodapiary.meteo_p.ow.dto.OwAlertDto;
import com.woodapiary.meteo_p.ow.dto.OwCurrentDto;
import com.woodapiary.meteo_p.ow.dto.OwDailyDto;
import com.woodapiary.meteo_p.ow.dto.OwHourlyDto;
import com.woodapiary.meteo_p.ow.dto.OwMessageDto;
import com.woodapiary.meteo_p.ow.dto.OwRainDto;
import com.woodapiary.meteo_p.ow.entities.OwAlert;
import com.woodapiary.meteo_p.ow.entities.OwDaily;
import com.woodapiary.meteo_p.ow.entities.OwFact;
import com.woodapiary.meteo_p.ow.entities.OwHourly;
import com.woodapiary.meteo_p.ow.entities.OwMessage;
import com.woodapiary.meteo_p.ow.service.OwMessageDtoEntityMapper;
import com.woodapiary.meteo_p.util.ObjectSerializator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OwMessageDtoEntityMapperTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ow_onecall.json";
    //private final String testDataFile2 = "ow_onecall_d.json";
    @Autowired
    private OwMessageDtoEntityMapper mapper;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final OwMessageDto dto1 = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
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
        assertTrue(dto1.toString().length() > 0);
    }

    @Test
    public void test03() throws IOException {
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
        final OwCurrentDto dto1 = dto.getCurrent();
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
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
        final List<OwAlertDto> dto1 = dto.getAlerts();
        final List<OwAlert> entity = mapper.alertListDtoToAlertList(dto1);
        final List<OwAlertDto> dto2 = mapper.alertListDtoFromAlertList(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void test05() throws IOException {
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
        final List<OwDailyDto> dto1 = dto.getDaily();
        final List<OwDaily> entity = mapper.dailyListDtoToDailyList(dto1);
        final List<OwDailyDto> dto2 = mapper.dailyListDtoFromDailyList(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void test06() throws IOException {
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().readJsonFromFile(testDataPath + testDataFile, OwMessageDto.class);
        final List<OwHourlyDto> dto1 = dto.getHourly();
        final List<OwHourly> entity = mapper.hourlyListDtoToHourlyList(dto1);
        final List<OwHourlyDto> dto2 = mapper.hourlyListDtoFromHourlyList(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

}
