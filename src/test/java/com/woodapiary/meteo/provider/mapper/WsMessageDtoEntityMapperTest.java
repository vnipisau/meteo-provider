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
import com.woodapiary.meteo.provider.service.WsMessageService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WsMessageDtoEntityMapperTest {

    @Value("${meteo-provider.provider.testdata.path}")
    private String testDataPath;
    private final String testDataFile = "ws.json";
    @Autowired
    private WsMessageDtoEntityMapper mapper;
    @Autowired
    private WsMessageService requester;

    @Test
    public void test01() {
        assertNotNull(mapper);
    }

    @Test
    public void test02() throws IOException {
        final WsMessageDto dto1 = requester.readFromFile(testDataPath + testDataFile);
        final WsMessage entity = mapper.messageDtoToMessage(dto1);
        final WsMessageDto dto2 = mapper.messageDtoFromMessage(entity);
        dto1.setLocation(null);
        dto1.setCurrent(null);
        dto1.setRequest(null);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

    @Test
    public void test03() throws IOException {
        final WsCurrentDto dto1 = requester.readFromFile(testDataPath + testDataFile).getCurrent();
        final WsFact entity = mapper.factDtoToFact(dto1);
        final WsCurrentDto dto2 = mapper.factDtoFromFact(entity);
        assertNotNull(dto2);
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertTrue(dto1.equals(dto2));
    }

}
