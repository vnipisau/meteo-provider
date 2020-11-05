/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

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
        messageRepo.save(entity);
        if (entity.getFact() != null) {
            entity.getFact().setMessage(entity);
            factRepo.save(entity.getFact());
        }
        final YaForecast forecast = entity.getForecast();
        if (forecast != null) {
            forecast.setMessage(entity);
            foreRepo.save(forecast);
            for (final YaPart part : forecast.getParts()) {
                part.setForecast(forecast);
                partRepo.save(part);
            }
        }
        return entity;
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
    @Transactional
    public List<YaFact> findBySource(String sourceId) {
        return factRepo.findBySource(sourceId);
    }

    @Override
    public long countMessages() {
        return messageRepo.count();
    }

    @Override
    public long countFacts() {
        return factRepo.count();
    }

    @Override
    public long countForecast() {
        return foreRepo.count();
    }

    @Override
    public long countParts() {
        return partRepo.count();
    }

}
