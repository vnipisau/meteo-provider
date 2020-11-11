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
import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwCurrentDto;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.mapper.OwMessageDtoEntityMapper;

@Service
public class OwMessageService {

    static Logger log = LoggerFactory.getLogger(OwMessageService.class);

    @Autowired
    OwDao dao;
    @Autowired
    OwMessageDtoEntityMapper mapper;
    @Autowired
    SourceDao sRepo;

    public void saveMessageToDb(final OwMessageDto dto, String provider, String location) {
        final OwMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), provider, location);
        log.info("save openweather message to db - ok, id=" + message.getMessageId());
    }

    public void saveMessagesToDb(final List<OwMessageDto> dto, String provider, String location) {
        final List<OwMessage> messages = dao.saveMessages(mapper.messagesDtoToMessages(dto), provider, location);
        log.info("save openweather messages to db - ok, count=" + messages.size());
    }

    public List<OwCurrentDto> getFacts(String provider, String location) {
        final List<OwFact> entityList = dao.findFacts(provider, location);
        final List<OwCurrentDto> res = mapper.factsDtoFromFacts(entityList);
        return res;
    }

    public OwMessageDto getLastMessage(String provider, String location) {
        final OwMessage ent = dao.findLastMessage(provider, location);
        final OwMessageDto dto = mapper.messageDtoFromMessage(ent);
        return dto;
    }

    public List<OwMessageDto> getMessages(String provider, String location) {
        final List<OwMessage> ent = dao.findMessages(provider, location);
        final List<OwMessageDto> dto = mapper.messagesDtoFromMessages(ent);
        return dto;
    }

}
