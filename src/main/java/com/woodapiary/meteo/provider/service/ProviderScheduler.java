/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.provider.dao.MeteoDao;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.entity.Source;

@Service
public class ProviderScheduler {

    static Logger log = LoggerFactory.getLogger(ProviderScheduler.class);

    @Autowired
    ProviderRequster requster;
    @Autowired
    YaMessageService serviceYa;
    @Autowired
    WsMessageService serviceWs;
    @Autowired
    OwMessageService serviceOw;
    @Value("${meteo-provider.provider.ya.enabled}")
    private Boolean providerYaEnabled;
    @Value("${meteo-provider.provider.ws.enabled}")
    private Boolean providerWsEnabled;
    @Value("${meteo-provider.provider.ow.enabled}")
    private Boolean providerOwEnabled;
    @Autowired
    MeteoDao sRepo;

    final int mFixedRate = 3600 * 2;

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 5000)
    public void runYa() {
        if (!providerYaEnabled) {
            return;
        }
        log.info("yandex weather scheduler started ok");
        YaMessageDto dto = null;
        for (final Source source : sRepo.findSourceByProvider(ProviderConst.providerYa)) {
            try {
                dto = requster.requestYa(source);
                serviceYa.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ya message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 6000)
    public void runWs() {
        if (!providerWsEnabled) {
            return;
        }
        log.info("ws weather scheduler started ok");
        WsMessageDto dto = null;
        for (final Source source : sRepo.findSourceByProvider(ProviderConst.providerWs)) {
            try {
                dto = requster.requestWs(source);
                serviceWs.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ws message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

    @Scheduled(fixedRate = 1000 * mFixedRate / 10, initialDelay = 7000)
    public void runOw() {
        if (!providerOwEnabled) {
            return;
        }
        log.info("ow weather scheduler started ok");
        OwMessageDto dto = null;
        for (final Source source : sRepo.findSourceByProvider(ProviderConst.providerOw)) {
            try {
                dto = requster.requestOw(source);
                serviceOw.saveMessageToDb(dto, source.getProvider(), source.getGeoname());
            } catch (final Exception e) {
                e.printStackTrace();
                log.error("ow message is " + (dto == null ? "null" : dto.toString()));
            }
        }
    }

}
