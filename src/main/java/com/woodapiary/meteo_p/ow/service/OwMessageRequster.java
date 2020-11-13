/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.ow.dao.OwConst;
import com.woodapiary.meteo_p.ow.dto.OwMessageDto;
import com.woodapiary.meteo_p.util.ObjectSerializator;

@Service
public class OwMessageRequster {

    static Logger log = LoggerFactory.getLogger(OwMessageRequster.class);

    @Value("${OPENWEATHERMAP_API_KEY}")
    public String apiKeyOw;
    @Value("${meteo-provider.provider.ow.enabled}")
    private Boolean providerOwEnabled;
    @Autowired
    OwMessageService serviceOw;
    @Autowired
    SourceDao sRepo;

    //Free 60 calls/minute 1,000,000 calls/month
    public OwMessageDto request(final Source source) throws IOException {
        //System.out.println(prop.getApiKey());
        final String url = source.getUrl() + "?" + "lat=" + source.getLat() + "&" + "lon=" + source.getLon()
                + "&" + "exclude=minutely" + "&" + "appid=" + apiKeyOw + "&" + "units=metric";
        final OwMessageDto dto = new ObjectSerializator<OwMessageDto>().requestJsonFromUrl(url, OwMessageDto.class);
        log.info("read openweather message ok from " + url);
        return dto;
    }

    public void run() {
        if (!providerOwEnabled) {
            return;
        }
        log.info("ow weather scheduler started ok");
        OwMessageDto dto = null;
        for (final Source source : sRepo.findSourcesByProvider(OwConst.providerOw)) {
            try {
                dto = request(source);
                serviceOw.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ow message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }
}
