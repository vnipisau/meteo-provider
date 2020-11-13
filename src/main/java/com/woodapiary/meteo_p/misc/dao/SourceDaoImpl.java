/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.misc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woodapiary.meteo_p.misc.entities.Source;
import com.woodapiary.meteo_p.misc.repo.SourceRepository;

//TODO кеш сорца
//TODO geoname by provider

@Repository
public class SourceDaoImpl implements SourceDao {

    @Autowired
    private SourceRepository sRepo;

    @Override
    public Source saveSource(Source entity) {
        return sRepo.save(entity);
    }

    @Override
    public void deleteAll() {
        sRepo.deleteAll();
    }

    @Override
    public List<Source> findSourcesByProvider(String provider) {
        return sRepo.findByProvider(provider);
    }

    @Override
    public Source findBySourceName(String provider, String location) {
        final String sourceName = provider + "-" + location;
        return sRepo.findBySourceName(sourceName);
    }

}
