/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.ya.dao.YaDao;
import com.woodapiary.meteo_p.ya.dto.YaFactDto;
import com.woodapiary.meteo_p.ya.dto.YaMessageDto;
import com.woodapiary.meteo_p.ya.entities.YaFact;
import com.woodapiary.meteo_p.ya.entities.YaMessage;

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
    SourceDao sRepo;
    @Autowired
    YaMessageRequster requseter;

    public void saveMessageToDb(final YaMessageDto dto, String provider, String location) {
        final YaMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), provider, location);
        log.info("save yandex weather message to db - ok, id= " + message.getMessageId());
    }

    public void saveMessagesToDb(final List<YaMessageDto> dto, String provider, String location) {
        final List<YaMessage> messages = dao.saveMessages(mapper.messagesDtoToMessages(dto), provider, location);
        log.info("save ws messages to db - ok, count=" + messages.size());
    }

    public List<YaFactDto> getFacts(String provider, String location) {
        final List<YaFact> entityList = dao.findFacts(provider, location);
        final List<YaFactDto> res = mapper.factsDtoFromFacts(entityList);
        return res;
    }

    public YaMessageDto getLastMessage(String provider, String location) {
        final YaMessage ent = dao.findLastMessage(provider, location);
        final YaMessageDto dto = mapper.messageDtoFromMessage(ent);
        return dto;
    }

    public void requestMessagesFromProvider() {
        requseter.run();
    }
}
