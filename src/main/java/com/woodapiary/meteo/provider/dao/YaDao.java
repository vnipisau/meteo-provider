/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;

public interface YaDao {

    YaMessage saveMessage(YaMessage message, String sourceName);

    void deleteAllMessages();

    List<YaFact> findBySource(String sourceName);

    long countMessages();

    long countFacts();

    long countForecast();

    long countParts();

    YaMessage findLastMessage(String sourceName);

}
