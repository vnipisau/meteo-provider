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
import com.woodapiary.meteo.provider.entity.ow.OwAlert;
import com.woodapiary.meteo.provider.entity.ow.OwDaily;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwHourly;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;
import com.woodapiary.meteo.provider.repo.ow.OwAlertRepository;
import com.woodapiary.meteo.provider.repo.ow.OwDailyRepository;
import com.woodapiary.meteo.provider.repo.ow.OwFactRepository;
import com.woodapiary.meteo.provider.repo.ow.OwHourlyRepository;
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
    @Autowired
    private OwAlertRepository alertRepo;
    @Autowired
    private OwDailyRepository dailyRepo;
    @Autowired
    private OwHourlyRepository hourlyRepo;

    @Override
    @Transactional
    public OwMessage saveMessage(OwMessage entity, Source source) {
        entity.setSource(source);
        messageRepo.save(entity);
        if (entity.getMfact() != null) {
            entity.getMfact().setMessage(entity);
            factRepo.save(entity.getMfact());
        }
        final List<OwDaily> daily = entity.getDaily();
        if (daily != null) {
            for (final OwDaily d : daily) {
                d.setMessage(entity);
                dailyRepo.save(d);
            }
        }
        final List<OwHourly> hourly = entity.getHourly();
        if (hourly != null) {
            for (final OwHourly d : hourly) {
                d.setMessage(entity);
                hourlyRepo.save(d);
            }
        }
        final List<OwAlert> alerts = entity.getAlerts();
        if (alerts != null) {
            for (final OwAlert d : alerts) {
                d.setMessage(entity);
                alertRepo.save(d);
            }
        }
        return entity;
    }

    @Override
    @Transactional
    public void deleteAllMessages() {
        alertRepo.deleteAll();
        factRepo.deleteAll();
        dailyRepo.deleteAll();
        hourlyRepo.deleteAll();
        messageRepo.deleteAll();
    }

    @Override
    @Transactional
    public List<OwFact> findBySource(String sourceId) {
        return factRepo.findBySource(sourceId);
    }

    @Override
    public long countMessages() {
        return messageRepo.count();
    }

    @Override
    @Transactional
    public void saveWeatherConditionCodes(List<OwWeather> weather) {
        for (final OwWeather w : weather) {
            weatherRepo.save(w);
        }
    }

    @Override
    @Transactional
    public void deleteWeatherConditionCodes() {
        weatherRepo.deleteAll();
    }

}
