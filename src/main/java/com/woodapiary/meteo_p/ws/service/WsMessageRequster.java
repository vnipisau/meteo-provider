/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.util.ObjectSerializator;
import com.woodapiary.meteo_p.ws.dao.WsConst;
import com.woodapiary.meteo_p.ws.dto.WsMessageDto;

@Service
public class WsMessageRequster {

    static Logger log = LoggerFactory.getLogger(WsMessageRequster.class);

    @Value("${WEATHERSTACK_API_KEY}")
    private String apiKeyWs;
    @Value("${meteo-provider.provider.ws.enabled}")
    private Boolean providerWsEnabled;
    @Autowired
    WsMessageService serviceWs;
    @Autowired
    SourceDao sRepo;

    //1.000 Calls / month
    public WsMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "access_key=" + apiKeyWs + "&" + "query=" + source.getLat() + "," + source.getLon();
        final WsMessageDto dto = new ObjectSerializator<WsMessageDto>().requestJsonFromUrl(url, WsMessageDto.class);
        log.info("read weatherstack message ok from " + url);
        return dto;

    }

    public void run() {
        if (!providerWsEnabled) {
            return;
        }
        log.info("ws weather scheduler started ok");
        WsMessageDto dto = null;
        for (final Source source : sRepo.findSourcesByProvider(WsConst.providerWs)) {
            try {
                dto = request(source);
                serviceWs.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ws message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

}
