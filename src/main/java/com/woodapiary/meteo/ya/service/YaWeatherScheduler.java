/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.ya.dto.MessageDto;
import com.woodapiary.meteo.ya.entity.Source;
import com.woodapiary.meteo.ya.repo.SourceRepository;

@Service
public class YaWeatherScheduler {

    static Logger log = LoggerFactory.getLogger(YaWeatherScheduler.class);

    @Autowired
    YaMessageService requster;
    @Autowired
    SourceRepository sRepo;

    final int mFixedRate = 3600;

    @Scheduled(fixedRate = 1000 * mFixedRate, initialDelay = 3000)
    public void run() throws MalformedURLException, IOException {
        log.info("yandex weather scheduler started ok");
        for (final Source source : sRepo.findAll()) {
            final MessageDto dto = requster.request(source);
            requster.saveToDb(dto, source);
        }
    }

}
