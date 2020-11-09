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
    MeteoDao sRepo;

    public void saveMessageToDb(final OwMessageDto dto, final String sourceName) {
        final OwMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), sourceName);
        log.info("save openweather message to db - ok, id=" + message.getMessageId());
    }

    public void saveMessagesToDb(final List<OwMessageDto> dto, final String sourceName) {
        final List<OwMessage> messages = dao.saveMessages(mapper.messagesDtoToMessages(dto), sourceName);
        log.info("save openweather messages to db - ok, count=" + messages.size());
    }

    public List<OwCurrentDto> getFacts(String sourceName) {
        final List<OwFact> entityList = dao.findFacts(sourceName);
        final List<OwCurrentDto> res = mapper.factsDtoFromFacts(entityList);
        return res;
    }

    public OwMessageDto getLastMessage(String sourceName) {
        final OwMessage ent = dao.findLastMessage(sourceName);
        final OwMessageDto dto = mapper.messageDtoFromMessage(ent);
        return dto;
    }

    public List<OwMessageDto> getMessages(String sourceName) {
        final List<OwMessage> ent = dao.findMessages(sourceName);
        final List<OwMessageDto> dto = mapper.messagesDtoFromMessages(ent);
        return dto;
    }

}
