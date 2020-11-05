/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;

@Repository
public interface YaDao {

    YaMessage saveMessage(YaMessage message, Source source);

    void deleteAllMessages();

    List<YaFact> findBySource(String sourceId);

    long countMessages();

    long countFacts();

    long countForecast();

    long countParts();

}
