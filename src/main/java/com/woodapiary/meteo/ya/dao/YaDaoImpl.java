/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.ya.entity.Fact;
import com.woodapiary.meteo.ya.entity.Forecast;
import com.woodapiary.meteo.ya.entity.Message;
import com.woodapiary.meteo.ya.entity.Part;
import com.woodapiary.meteo.ya.entity.Source;
import com.woodapiary.meteo.ya.repo.FactRepository;
import com.woodapiary.meteo.ya.repo.ForecastRepository;
import com.woodapiary.meteo.ya.repo.MessageRepository;
import com.woodapiary.meteo.ya.repo.PartRepository;

@Component
public class YaDaoImpl implements YaDao {

    @Autowired
    private MessageRepository messageRepo;
    @Autowired
    private FactRepository factRepo;
    @Autowired
    private ForecastRepository foreRepo;
    @Autowired
    private PartRepository partRepo;

    @Override
    @Transactional
    public Message saveMessage(final Message entity, final Source source) {
        entity.setSource(source);
        //System.out.println(entity);
        return messageRepo.save(entity);
    }

    @Override
    @Transactional
    public Fact saveFact(final Message message, final Fact fact) {
        fact.setMessage(message);
        return factRepo.save(fact);
    }

    @Override
    @Transactional
    public Forecast saveForecast(final Message message, final Forecast forecast, final List<Part> parts) {
        forecast.setMessage(message);
        forecast.setParts(new ArrayList<>());
        for (final Part part : parts) {
            forecast.addPart(part);
        }
        foreRepo.save(forecast);
        for (final Part part : parts) {
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

}
