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
import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.dto.ya.YaFactDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.mapper.YaMessageDtoEntityMapper;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@Service
public class YaMessageService {

    static Logger log = LoggerFactory.getLogger(YaMessageService.class);
    public static final String provider = "yandex";
    @Value("${YANDEX_API_KEY}")
    private String apiKey;

    @Autowired
    YaDao dao;
    @Autowired
    YaMessageDtoEntityMapper mapper;
    @Autowired
    MeteoDao sRepo;

    //50 в сутки.
    public YaMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon();
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().requestJsonFromUrl(url, YaMessageDto.class);
        log.info("read yandex weather message   ok from " + url);
        return dto;
    }

    public void saveToDb(final YaMessageDto dto, final Source source) {
        final YaMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), source);
        log.info("save yandex weather message to db - ok, id= " + message.getMessageId());
    }

    public void requestAllAndSave() {
        YaMessageDto dto = null;
        for (final Source source : sRepo.findSourceByProvider(provider)) {
            try {
                dto = request(source);
                saveToDb(dto, source);
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ya message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

    public List<YaFactDto> getFacts(String sourceId) {
        final List<YaFactDto> res = new ArrayList<>();
        final List<YaFact> src = dao.findBySource(sourceId);
        for (final YaFact entity : src) {
            res.add(mapper.factDtoFromFact(entity));
        }
        return res;
    }

}
