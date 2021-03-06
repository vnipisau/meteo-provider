/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woodapiary.meteo_p.misc.dao.SourceDao;
import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.ya.entities.YaFact;
import com.woodapiary.meteo_p.ya.entities.YaForecast;
import com.woodapiary.meteo_p.ya.entities.YaMessage;
import com.woodapiary.meteo_p.ya.entities.YaPart;
import com.woodapiary.meteo_p.ya.repo.YaFactRepository;
import com.woodapiary.meteo_p.ya.repo.YaForecastRepository;
import com.woodapiary.meteo_p.ya.repo.YaMessageRepository;
import com.woodapiary.meteo_p.ya.repo.YaPartRepository;

@Repository
public class YaDaoImpl implements YaDao {

    @Autowired
    private YaMessageRepository messageRepo;
    @Autowired
    private YaFactRepository factRepo;
    @Autowired
    private YaForecastRepository foreRepo;
    @Autowired
    private YaPartRepository partRepo;
    @Autowired
    private SourceDao meteoDao;

    @Override
    @Transactional
    public YaMessage saveMessage(final YaMessage entity, String provider, String location) {
        final Source source = meteoDao.findBySourceName(provider, location);
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
    public List<YaFact> findFacts(String provider, String location) {
        final Source src = meteoDao.findBySourceName(provider, location);
        return factRepo.findBySource(src.getSourceId());
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

    @Override
    @Transactional
    public YaMessage findLastMessage(String provider, String location) {
        final Source src = meteoDao.findBySourceName(provider, location);
        return messageRepo.findLastMessage(src.getSourceId());
    }

    @Override
    public List<YaMessage> saveMessages(List<YaMessage> messages, String provider, String location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<YaMessage> findMessages(String provider, String location) {
        // TODO Auto-generated method stub
        return null;
    }

}
