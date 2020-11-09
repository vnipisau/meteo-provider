/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

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

    public void saveMessageToDb(final WsMessageDto dto, final String sourceName) {
        final WsMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), sourceName);
        log.info("save ws weather message to db - ok, id= " + message.getMessageId());
    }

    public void saveMessagesToDb(final List<WsMessageDto> dto, final String sourceName) {
        final List<WsMessage> messages = dao.saveMessages(mapper.messagesDtoToMessages(dto), sourceName);
        log.info("save ws messages to db - ok, count=" + messages.size());
    }

    public List<WsCurrentDto> getFacts(String sourceName) {
        final List<WsFact> entityList = dao.findFacts(sourceName);
        final List<WsCurrentDto> res = mapper.factsDtoFromFacts(entityList);
        return res;
    }

    public WsMessageDto getLastMessage(String sourceName) {
        final WsMessage ent = dao.findLastMessage(sourceName);
        final WsMessageDto dto = mapper.messageDtoFromMessage(ent);
        return dto;
    }

    public List<WsMessageDto> getMessages(String sourceName) {
        final List<WsMessage> ent = dao.findMessages(sourceName);
        final List<WsMessageDto> dto = mapper.messagesDtoFromMessages(ent);
        return dto;
    }

}
