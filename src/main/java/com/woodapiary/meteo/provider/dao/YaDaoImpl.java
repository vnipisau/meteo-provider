/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.entity.ya.YaPart;
import com.woodapiary.meteo.provider.repo.ya.YaFactRepository;
import com.woodapiary.meteo.provider.repo.ya.YaForecastRepository;
import com.woodapiary.meteo.provider.repo.ya.YaMessageRepository;
import com.woodapiary.meteo.provider.repo.ya.YaPartRepository;

@Component
public class YaDaoImpl implements YaDao {

    @Autowired
    private YaMessageRepository messageRepo;
    @Autowired
    private YaFactRepository factRepo;
    @Autowired
    private YaForecastRepository foreRepo;
    @Autowired
    private YaPartRepository partRepo;

    @Override
    @Transactional
    public YaMessage saveMessage(final YaMessage entity, final Source source) {
        entity.setSource(source);
        //System.out.println(entity);
        return messageRepo.save(entity);
    }

    @Override
    @Transactional
    public YaFact saveFact(final YaMessage message, final YaFact fact) {
        fact.setMessage(message);
        return factRepo.save(fact);
    }

    @Override
    @Transactional
    public YaForecast saveForecast(final YaMessage message, final YaForecast forecast, final List<YaPart> parts) {
        forecast.setMessage(message);
        forecast.setParts(new ArrayList<>());
        for (final YaPart part : parts) {
            forecast.addPart(part);
        }
        foreRepo.save(forecast);
        for (final YaPart part : parts) {
            partRepo.save(part);
        }
        return forecast;
    }

    @Override
    @Transactional
    public void deleteAllMessages() {
        partRepo.deleteAll();
        factRepo.deleteAll();
        foreRepo.deleteAll();
        messageRepo.deleteAll();
    }

    @Override
    public List<YaFact> findBySource(String sourceId) {
        return factRepo.findBySource(sourceId);
    }

}
