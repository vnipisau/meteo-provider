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

import com.woodapiary.meteo.provider.dto.ws.WsCurrentDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.util.ObjectSerializator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsMessageDtoEntityMapperTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ws.json";
    @Autowired
    private WsMessageDtoEntityMapper mapper;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final WsMessageDto dto1 = new ObjectSerializator<WsMessageDto>().readJsonFromFile(testDataPath + testDataFile, WsMessageDto.class);
        dto1.setLocation(null);
        dto1.setCurrent(null);
        dto1.setRequest(null);
        final WsMessage entity = mapper.messageDtoToMessage(dto1);
        final WsMessageDto dto2 = mapper.messageDtoFromMessage(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
        assertTrue(dto1.toString().length() > 0);
    }

    @Test
    public void test03() throws IOException {
        final WsMessageDto dto = new ObjectSerializator<WsMessageDto>().readJsonFromFile(testDataPath + testDataFile, WsMessageDto.class);
        final WsCurrentDto dto1 = dto.getCurrent();
        final WsFact entity = mapper.factDtoToFact(dto1);
        final WsCurrentDto dto2 = mapper.factDtoFromFact(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

}
