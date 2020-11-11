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

import com.woodapiary.meteo.provider.dao.SourceDao;
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
    SourceDao sRepo;

    public void saveMessageToDb(final WsMessageDto dto, String provider, String location) {
        final WsMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), provider, location);
        log.info("save ws weather message to db - ok, id= " + message.getMessageId());
    }

    public void saveMessagesToDb(final List<WsMessageDto> dto, String provider, String location) {
        final List<WsMessage> messages = dao.saveMessages(mapper.messagesDtoToMessages(dto), provider, location);
        log.info("save ws messages to db - ok, count=" + messages.size());
    }

    public List<WsCurrentDto> getFacts(String provider, String location) {
        final List<WsFact> entityList = dao.findFacts(provider, location);
        final List<WsCurrentDto> res = mapper.factsDtoFromFacts(entityList);
        return res;
    }

    public WsMessageDto getLastMessage(String provider, String location) {
        final WsMessage ent = dao.findLastMessage(provider, location);
        final WsMessageDto dto = mapper.messageDtoFromMessage(ent);
        return dto;
    }

    public List<WsMessageDto> getMessages(String provider, String location) {
        final List<WsMessage> ent = dao.findMessages(provider, location);
        final List<WsMessageDto> dto = mapper.messagesDtoFromMessages(ent);
        return dto;
    }

}
