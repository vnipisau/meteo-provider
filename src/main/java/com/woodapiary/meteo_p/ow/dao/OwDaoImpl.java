/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.ow.entities.OwAlert;
import com.woodapiary.meteo_p.ow.entities.OwDaily;
import com.woodapiary.meteo_p.ow.entities.OwFact;
import com.woodapiary.meteo_p.ow.entities.OwHourly;
import com.woodapiary.meteo_p.ow.entities.OwMessage;
import com.woodapiary.meteo_p.ow.entities.OwWeather;
import com.woodapiary.meteo_p.ow.repo.OwAlertRepository;
import com.woodapiary.meteo_p.ow.repo.OwDailyRepository;
import com.woodapiary.meteo_p.ow.repo.OwFactRepository;
import com.woodapiary.meteo_p.ow.repo.OwHourlyRepository;
import com.woodapiary.meteo_p.ow.repo.OwMessageRepository;
import com.woodapiary.meteo_p.ow.repo.OwWeatherRepository;

@Repository
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
    @Autowired
    private SourceDao meteoDao;

    @Override
    @Transactional
    public OwMessage saveMessage(OwMessage entity, String provider, String location) {
        final Source source = meteoDao.findBySourceName(provider, location);
        entity.setSource(source);
        messageRepo.save(entity);
        if (entity.getFact() != null) {
            entity.getFact().setMessage(entity);
            factRepo.save(entity.getFact());
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
    public List<OwFact> findFacts(String provider, String location) {
        final Source src = meteoDao.findBySourceName(provider, location);
        return factRepo.findBySource(src.getSourceId());
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

    @Override
    public long countFacts() {
        return factRepo.count();
    }

    @Override
    public long countDaily() {
        return dailyRepo.count();
    }

    @Override
    public long countHourly() {
        return hourlyRepo.count();
    }

    @Override
    public long countAlerts() {
        return alertRepo.count();
    }

    @Override
    @Transactional
    public OwMessage findLastMessage(String provider, String location) {
        final Source src = meteoDao.findBySourceName(provider, location);
        return messageRepo.findLastMessage(src.getSourceId());
    }

    @Override
    public List<OwMessage> saveMessages(List<OwMessage> messages, String provider, String location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OwMessage> findMessages(String provider, String location) {
        // TODO Auto-generated method stub
        return null;
    }

}
