/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;

public interface OwDao {
    OwMessage saveMessage(OwMessage message, String sourceName);

    void deleteAllMessages();

    List<OwFact> findFacts(String sourceName);

    void saveWeatherConditionCodes(List<OwWeather> weather);

    void deleteWeatherConditionCodes();

    long countMessages();

    long countFacts();

    long countDaily();

    long countHourly();

    long countAlerts();

    OwMessage findLastMessage(String sourceName);

    List<OwMessage> saveMessages(List<OwMessage> messages, String sourceName);

    List<OwMessage> findMessages(String sourceName);
}
