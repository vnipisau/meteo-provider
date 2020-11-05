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
import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwCurrentDto;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.mapper.OwMessageDtoEntityMapper;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@Service
public class OwMessageService {

    static Logger log = LoggerFactory.getLogger(OwMessageService.class);
    public static final String provider = "openweathermap";

    @Value("${OPENWEATHERMAP_API_KEY}")
    public String apiKey;
    @Autowired
    OwDao dao;
    @Autowired
    OwMessageDtoEntityMapper mapper;
    @Autowired
    MeteoDao sRepo;

    //Free 60 calls/minute 1,000,000 calls/month
    public OwMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon()
                + "&" + "exclude=minutely" + "&" + "appid=" + apiKey + "&" + "units=metric";
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().requestJsonFromUrl(url, OwMessageDto.class);
        log.info("read openweather message ok from " + url);
        return dto;
    }

    public void saveToDb(final OwMessageDto dto, final Source source) {
        final OwMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), source);
        log.info("save openweather message to db - ok" + message.getMessageId());
    }

    public void requestAllAndSave() {
        OwMessageDto dto = null;
        for (final Source source : sRepo.findSourceByProvider(provider)) {
            try {
                dto = request(source);
                saveToDb(dto, source);
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ow message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

    public List<OwCurrentDto> getFacts(String sourceId) {
        final List<OwCurrentDto> res = new ArrayList<>();
        final List<OwFact> src = dao.findBySource(sourceId);
        for (final OwFact entity : src) {
            res.add(mapper.factDtoFromFact(entity));
        }
        return res;
    }

}
