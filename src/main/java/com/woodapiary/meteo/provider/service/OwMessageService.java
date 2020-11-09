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

    public void saveToDb(final OwMessageDto dto, final String sourceName) {
        final OwMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), sourceName);
        log.info("save openweather message to db - ok, id=" + message.getMessageId());
    }

    public List<OwCurrentDto> getFacts(String sourceName) {
        final List<OwCurrentDto> res = new ArrayList<>();
        final List<OwFact> src = dao.findBySource(sourceName);
        for (final OwFact entity : src) {
            res.add(mapper.factDtoFromFact(entity));
        }
        return res;
    }

}
