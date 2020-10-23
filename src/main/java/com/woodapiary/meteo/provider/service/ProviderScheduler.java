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

@Service
public class ProviderScheduler {

    static Logger log = LoggerFactory.getLogger(ProviderScheduler.class);

    @Autowired
    YaMessageService requsterYa;
    @Autowired
    WsMessageService requsterWs;
    @Autowired
    OwMessageService requsterOw;
    @Value("${meteo-provider.provider.ya.enabled}")
    private Boolean providerYaEnabled;
    @Value("${meteo-provider.provider.ws.enabled}")
    private Boolean providerWsEnabled;
    @Value("${meteo-provider.provider.ow.enabled}")
    private Boolean providerOwEnabled;

    final int mFixedRate = 3600 * 2;

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 5000)
    public void runYa() {
        if (!providerYaEnabled) {
            return;
        }
        log.info("yandex weather scheduler started ok");
        requsterYa.requestAllAndSave();
    }

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 6000)
    public void runWs() {
        if (!providerWsEnabled) {
            return;
        }
        log.info("ws weather scheduler started ok");
        requsterWs.requestAllAndSave();
    }

    @Scheduled(fixedRate = 1000 * mFixedRate / 10, initialDelay = 7000)
    public void runOw() {
        if (!providerOwEnabled) {
            return;
        }
        log.info("ow weather scheduler started ok");
        requsterOw.requestAllAndSave();
    }

}
