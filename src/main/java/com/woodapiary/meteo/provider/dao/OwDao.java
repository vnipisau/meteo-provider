/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ow.OwAlert;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;

@Repository
public interface OwDao {
    OwMessage saveMessage(OwMessage message, Source source);

    OwFact saveFact(OwMessage message, OwFact fact, List<OwWeather> weather);

    List<OwAlert> saveAlerts(OwMessage message, List<OwAlert> alerts);

    void deleteAllMessages();

    List<OwFact> findBySource(String sourceId);

    long count();
}
