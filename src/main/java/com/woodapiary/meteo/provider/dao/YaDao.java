/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.entity.ya.YaPart;

@Repository
public interface YaDao {

    YaMessage saveMessage(YaMessage message, Source source);

    YaFact saveFact(YaMessage message, YaFact fact);

    YaForecast saveForecast(YaMessage message, YaForecast forecast, List<YaPart> parts);

    void deleteAllMessages();

    List<YaFact> findBySource(String sourceId);

    long countMessages();

}
