/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo_p.ow.service.OwMessageService;
import com.woodapiary.meteo_p.ws.service.WsMessageService;
import com.woodapiary.meteo_p.ya.service.YaMessageService;

@Service
public class ProviderScheduler {

    static Logger log = LoggerFactory.getLogger(ProviderScheduler.class);

    @Autowired
    YaMessageService yaRequster;
    @Autowired
    WsMessageService wsRequster;
    @Autowired
    OwMessageService owRequster;

    final int mFixedRate = 3600 * 2;

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 5000)
    public void runYa() {
        yaRequster.requestMessagesFromProvider();
    }

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 6000)
    public void runWs() {
        wsRequster.requestMessagesFromProvider();
    }

    @Scheduled(fixedRate = 1000 * mFixedRate / 10, initialDelay = 7000)
    public void runOw() {
        owRequster.requestMessagesFromProvider();
    }

}
