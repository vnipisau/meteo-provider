/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.provider.dao.MeteoDao;
import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dto.ws.WsCurrentDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.mapper.WsMessageDtoEntityMapper;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@Service
public class WsMessageService {

    static Logger log = LoggerFactory.getLogger(WsMessageService.class);

    public static final String provider = "weatherstack";

    @Value("${WEATHERSTACK_API_KEY}")
    private String apiKey;

    @Autowired
    WsDao dao;
    @Autowired
    WsMessageDtoEntityMapper mapper;
    @Autowired
    MeteoDao sRepo;

    //1.000 Calls / month
    public WsMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "access_key=" + apiKey + "&" + "query=" + source.getLat() + "," + source.getLon();
        final WsMessageDto dto = new ObjectSerializator<WsMessageDto>().requestJsonFromUrl(url, WsMessageDto.class);
        log.info("read weatherstack message ok from " + url);
        return dto;

    }

    public void saveToDb(final WsMessageDto dto, final Source source) {
        final WsMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), source);
        //dao.saveFact(message, mapper.factDtoToFact(dto.getCurrent()));
        log.info("save ws weather message to db - ok " + message.getMessageId());
    }

    public void requestAllAndSave() {
        WsMessageDto dto = null;
        for (final Source source : sRepo.findSourceByProvider(provider)) {
            try {
                dto = request(source);
                saveToDb(dto, source);
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ws message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

    public List<WsCurrentDto> getFacts(String sourceId) {
        final List<WsCurrentDto> res = new ArrayList<>();
        final List<WsFact> src = dao.findBySource(sourceId);
        for (final WsFact entity : src) {
            res.add(mapper.factDtoFromFact(entity));
        }
        return res;
    }

}
