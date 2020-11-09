/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.provider.dao.MeteoDao;
import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dto.ws.WsCurrentDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.mapper.WsMessageDtoEntityMapper;

@Service
public class WsMessageService {

    static Logger log = LoggerFactory.getLogger(WsMessageService.class);

    @Autowired
    WsDao dao;
    @Autowired
    WsMessageDtoEntityMapper mapper;
    @Autowired
    MeteoDao sRepo;

    public void saveToDb(final WsMessageDto dto, final String sourceName) {
        final WsMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), sourceName);
        //dao.saveFact(message, mapper.factDtoToFact(dto.getCurrent()));
        log.info("save ws weather message to db - ok, id= " + message.getMessageId());
    }

    public List<WsCurrentDto> getFacts(String sourceName) {
        final List<WsCurrentDto> res = new ArrayList<>();
        final List<WsFact> src = dao.findBySource(sourceName);
        for (final WsFact entity : src) {
            res.add(mapper.factDtoFromFact(entity));
        }
        return res;
    }

}
