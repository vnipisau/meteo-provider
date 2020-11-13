/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.dao;

import java.util.List;

import com.woodapiary.meteo_p.ow.entities.OwFact;
import com.woodapiary.meteo_p.ow.entities.OwMessage;
import com.woodapiary.meteo_p.ow.entities.OwWeather;

public interface OwDao {
    OwMessage saveMessage(OwMessage message, String provider, String location);

    void deleteAllMessages();

    List<OwFact> findFacts(String provider, String location);

    void saveWeatherConditionCodes(List<OwWeather> weather);

    void deleteWeatherConditionCodes();

    long countMessages();

    long countFacts();

    long countDaily();

    long countHourly();

    long countAlerts();

    OwMessage findLastMessage(String provider, String location);

    List<OwMessage> saveMessages(List<OwMessage> messages, String provider, String location);

    List<OwMessage> findMessages(String provider, String location);
}
