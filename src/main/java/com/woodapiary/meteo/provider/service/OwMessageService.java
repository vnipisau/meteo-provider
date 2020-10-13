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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.woodapiary.meteo.provider.config.AppProperties;
import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;

@Service
public class OwMessageService {

    static Logger log = LoggerFactory.getLogger(OwMessageService.class);

    @Value("${OPENWEATHERMAP_API_KEY}")
    private String apiKey;

    @Autowired
    OwDao dao;
    //@Autowired
    //YaMessageDtoEntityMapper mapper;
    @Autowired
    AppProperties prop;
    @Autowired
    SourceRepository sRepo;

    //https://api.openweathermap.org/data/2.5/onecall?lat=55&lon=37&exclude=minutely&appid=2155573ef172407e333e91860fe3406d
    //Free 60 calls/minute 1,000,000 calls/month
    public OwMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon()
                + "&" + "exclude=minutely" + "&" + "appid=" + apiKey;

        URLConnection connection;
        connection = new URL(url).openConnection();
        try (InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
            final String response = rd.readLine();
            System.out.println(response);
            final OwMessageDto owDto = new Gson().fromJson(response, OwMessageDto.class);
            //System.out.println(wsDto.toString());
            rd.close();
            log.info("read openweather message ok from " + url);
            return owDto;
        }
    }

    public OwMessageDto readFromFile(final String path) throws IOException {
        final FileInputStream fis = new FileInputStream(path);
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")))) {
            final Gson parser = new GsonBuilder()
                    //.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
                    .create();
            final OwMessageDto owDto = parser.fromJson(rd, OwMessageDto.class);
            return owDto;
        }
    }

}
