/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.util.ObjectSerializator;
import com.woodapiary.meteo_p.ya.dao.YaConst;
import com.woodapiary.meteo_p.ya.dto.YaMessageDto;

@Service
public class YaMessageRequster {

    static Logger log = LoggerFactory.getLogger(YaMessageRequster.class);

    @Value("${YANDEX_API_KEY}")
    private String apiKeyYa;
    @Value("${meteo-provider.provider.ya.enabled}")
    private Boolean providerYaEnabled;
    @Autowired
    YaMessageService serviceYa;
    @Autowired
    SourceDao sRepo;

    //50 в сутки.
    public YaMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon();
        //connection.setRequestProperty("X-Yandex-API-Key", apiKey);
        final Map<String, String> props = new HashMap<>();
        props.put("X-Yandex-API-Key", apiKeyYa);
        final YaMessageDto dto = new ObjectSerializator<YaMessageDto>().requestJsonFromUrl(url, YaMessageDto.class, props);
        log.info("read yandex weather message   ok from " + url);
        return dto;
    }

    public void run() {
        if (!providerYaEnabled) {
            return;
        }
        log.info("yandex weather scheduler started ok");
        YaMessageDto dto = null;
        for (final Source source : sRepo.findSourcesByProvider(YaConst.providerYa)) {
            try {
                dto = request(source);
                serviceYa.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ya message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

}
