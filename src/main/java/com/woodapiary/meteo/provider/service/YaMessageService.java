/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.provider.dao.MeteoDao;
import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.dto.ya.YaFactDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.mapper.YaMessageDtoEntityMapper;

@Service
public class YaMessageService {

    static Logger log = LoggerFactory.getLogger(YaMessageService.class);

    @Value("${YANDEX_API_KEY}")
    private String apiKey;

    @Autowired
    YaDao dao;
    @Autowired
    YaMessageDtoEntityMapper mapper;
    @Autowired
    MeteoDao sRepo;

    public void saveMessageToDb(final YaMessageDto dto, final String sourceName) {
        final YaMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), sourceName);
        log.info("save yandex weather message to db - ok, id= " + message.getMessageId());
    }

    public void saveMessagesToDb(final List<YaMessageDto> dto, final String sourceName) {
        final List<YaMessage> messages = dao.saveMessages(mapper.messagesDtoToMessages(dto), sourceName);
        log.info("save ws messages to db - ok, count=" + messages.size());
    }

    public List<YaFactDto> getFacts(String sourceName) {
        final List<YaFact> entityList = dao.findFacts(sourceName);
        final List<YaFactDto> res = mapper.factsDtoFromFacts(entityList);
        return res;
    }

    public YaMessageDto getLastMessage(String sourceName) {
        final YaMessage ent = dao.findLastMessage(sourceName);
        final YaMessageDto dto = mapper.messageDtoFromMessage(ent);
        return dto;
    }

}
