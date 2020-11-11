/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.util.ObjectSerializator;

@Service
public class ProviderRequster {

    static Logger log = LoggerFactory.getLogger(ProviderRequster.class);

    @Value("${YANDEX_API_KEY}")
    private String apiKeyYa;
    @Value("${OPENWEATHERMAP_API_KEY}")
    public String apiKeyOw;
    @Value("${WEATHERSTACK_API_KEY}")
    private String apiKeyWs;

    //50 в сутки.
    public YaMessageDto requestYa(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon();
        //connection.setRequestProperty("X-Yandex-API-Key", apiKey);
        final Map<String, String> props = new HashMap<>();
        props.put("X-Yandex-API-Key", apiKeyYa);
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().requestJsonFromUrl(url, YaMessageDto.class, props);
        log.info("read yandex weather message   ok from " + url);
        return dto;
    }

    //1.000 Calls / month
    public WsMessageDto requestWs(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "access_key=" + apiKeyWs + "&" + "query=" + source.getLat() + "," + source.getLon();
        final WsMessageDto dto = new ObjectSerializator<WsMessageDto>().requestJsonFromUrl(url, WsMessageDto.class);
        log.info("read weatherstack message ok from " + url);
        return dto;

    }

    //Free 60 calls/minute 1,000,000 calls/month
    public OwMessageDto requestOw(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon()
                + "&" + "exclude=minutely" + "&" + "appid=" + apiKeyOw + "&" + "units=metric";
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().requestJsonFromUrl(url, OwMessageDto.class);
        log.info("read openweather message ok from " + url);
        return dto;
    }

}
