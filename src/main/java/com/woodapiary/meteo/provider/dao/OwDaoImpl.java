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
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;
import com.woodapiary.meteo.provider.repo.ow.OwFactRepository;
import com.woodapiary.meteo.provider.repo.ow.OwMessageRepository;
import com.woodapiary.meteo.provider.repo.ow.OwWeatherRepository;

@Component
public class OwDaoImpl implements OwDao {

    @Autowired
    private OwMessageRepository messageRepo;
    @Autowired
    private OwFactRepository factRepo;
    @Autowired
    private OwWeatherRepository weatherRepo;

    @Override
    @Transactional
    public OwMessage saveMessage(OwMessage entity, Source source) {
        entity.setSource(source);
        //System.out.println(entity);
        return messageRepo.save(entity);
    }

    @Override
    @Transactional
    public OwFact saveFact(OwMessage message, OwFact fact, List<OwWeather> weather) {
        fact.setMessage(message);
        fact.setWeather(new ArrayList<>());
        for (final OwWeather w : weather) {
            fact.addWeather(w);
        }
        factRepo.save(fact);
        for (final OwWeather w : weather) {
            weatherRepo.save(w);
        }
        return factRepo.save(fact);
    }

    @Override
    @Transactional
    public void deleteAllMessages() {
        weatherRepo.deleteAll();
        factRepo.deleteAll();
        messageRepo.deleteAll();
    }

    @Override
    @Transactional
    public List<OwFact> findBySource(String sourceId) {
        return factRepo.findBySource(sourceId);
    }

    @Override
    public long count() {
        return messageRepo.count();
    }

}
