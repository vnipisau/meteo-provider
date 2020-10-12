/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ProviderScheduler {

    static Logger log = LoggerFactory.getLogger(ProviderScheduler.class);

    @Autowired
    YaMessageService requster;

    final int mFixedRate = 3600;

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 5000)
    public void run() {
        log.info("yandex weather scheduler started ok");
        requster.requestAllAndSave();
    }

}
