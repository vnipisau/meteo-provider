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

    public void saveToDb(final YaMessageDto dto, final String sourceName) {
        final YaMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), sourceName);
        log.info("save yandex weather message to db - ok, id= " + message.getMessageId());
    }

    public List<YaFactDto> getFacts(String sourceName) {
        final List<YaFactDto> res = new ArrayList<>();
        final List<YaFact> src = dao.findBySource(sourceName);
        for (final YaFact entity : src) {
            res.add(mapper.factDtoFromFact(entity));
        }
        return res;
    }

}
