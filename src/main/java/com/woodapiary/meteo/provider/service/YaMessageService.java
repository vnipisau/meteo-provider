/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woodapiary.meteo.provider.config.AppProperties;
import com.woodapiary.meteo.provider.dao.YaDao;
import com.woodapiary.meteo.provider.dto.ya.YaFactDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.mapper.YaMessageDtoEntityMapper;
import com.woodapiary.meteo.provider.repo.SourceRepository;

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
    AppProperties prop;
    @Autowired
    SourceRepository sRepo;

    //50 в сутки.
    public YaMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon();
        URLConnection connection;
        connection = new URL(url).openConnection();
        connection.setRequestProperty("X-Yandex-API-Key", apiKey);
        try (InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
            final String response = rd.readLine();
            //System.out.println(response);
            final YaMessageDto ywDto = new Gson().fromJson(response, YaMessageDto.class);
            //System.out.println(ywDto.toString());
            rd.close();
            log.info("read yandex weather message   ok from " + url);
            return ywDto;
        }
    }

    public YaMessageDto readFromFile(final String path) throws IOException {
        final FileInputStream fis = new FileInputStream(path);
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")))) {
            final Gson parser = new GsonBuilder()
                    //.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
                    .create();
            final YaMessageDto ywDto = parser.fromJson(rd, YaMessageDto.class);
            return ywDto;
        }
    }

    public void saveToDb(final YaMessageDto dto, final Source source) {
        final YaMessage message = dao.saveMessage(mapper.messageDtoToMessage(dto), source);
        log.debug(message.toString());
        final YaFact fact = dao.saveFact(message, mapper.factDtoToFact(dto.getFact()));
        log.debug(fact.toString());
        final YaForecast forecast = dao.saveForecast(message, mapper.forecastDtoToForecast(dto.getForecast()), mapper.partListDtoToPartList(dto.getForecast().getParts()));
        log.debug(forecast.toString());
        log.info("save yandex weather message to db - ok");
    }

    public void requestAllAndSave() {
        for (final Source source : sRepo.findAll()) {
            try {
                final YaMessageDto dto = request(source);
                saveToDb(dto, source);
            } catch (final IOException e) {
                e.printStackTrace();
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
