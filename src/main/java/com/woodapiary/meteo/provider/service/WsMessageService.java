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
import com.woodapiary.meteo.provider.dao.WsDao;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;

@Service
public class WsMessageService {

    static Logger log = LoggerFactory.getLogger(WsMessageService.class);

    @Value("${WEATHERSTACK_API_KEY}")
    private String apiKey;

    @Autowired
    WsDao dao;
    //@Autowired
    //YaMessageDtoEntityMapper mapper;
    @Autowired
    AppProperties prop;
    @Autowired
    SourceRepository sRepo;

    //1.000 Calls / month
    public WsMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "access_key=" + apiKey + "&" + "query=" + source.getLat() + "," + source.getLon();
        URLConnection connection;
        connection = new URL(url).openConnection();
        try (InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));) {
            final String response = rd.readLine();
            System.out.println(response);
            final WsMessageDto wsDto = new Gson().fromJson(response, WsMessageDto.class);
            //System.out.println(wsDto.toString());
            rd.close();
            log.info("read weatherstack message ok from " + url);
            return wsDto;
        }
    }

    public WsMessageDto readFromFile(final String path) throws IOException {
        final FileInputStream fis = new FileInputStream(path);
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")))) {
            final Gson parser = new GsonBuilder()
                    //.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter().nullSafe())
                    .create();
            final WsMessageDto wsDto = parser.fromJson(rd, WsMessageDto.class);
            return wsDto;
        }
    }

}
