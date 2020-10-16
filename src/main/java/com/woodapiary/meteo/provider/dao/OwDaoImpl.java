/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;

@Component
public class OwDaoImpl implements OwDao {

    @Override
    public OwMessage saveMessage(OwMessage message, Source source) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OwFact saveFact(OwMessage message, OwFact fact) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteAllMessages() {
        // TODO Auto-generated method stub

    }

    @Override
    public List<OwFact> findBySource(String sourceId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}
