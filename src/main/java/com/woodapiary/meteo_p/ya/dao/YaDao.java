/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.dao;

import java.util.List;

import com.woodapiary.meteo_p.ya.entities.YaFact;
import com.woodapiary.meteo_p.ya.entities.YaMessage;

public interface YaDao {

    YaMessage saveMessage(YaMessage message, String provider, String location);

    void deleteAllMessages();

    List<YaFact> findFacts(String provider, String location);

    long countMessages();

    long countFacts();

    long countForecast();

    long countParts();

    YaMessage findLastMessage(String provider, String location);

    List<YaMessage> saveMessages(List<YaMessage> messages, String provider, String location);

    List<YaMessage> findMessages(String provider, String location);

}
