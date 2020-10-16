/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;

@Repository
public interface OwDao {
    OwMessage saveMessage(OwMessage message, Source source);

    OwFact saveFact(OwMessage message, OwFact fact);

    void deleteAllMessages();

    List<OwFact> findBySource(String sourceId);

    long count();
}
