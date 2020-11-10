/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.repo.SourceRepository;

@Repository
public class MeteoDaoImpl implements MeteoDao {

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
    public List<Source> findSourceByProvider(String provider) {
        return sRepo.findByProvider(provider);
    }

    @Override
    public Source findBySourceName(String provider, String location) {
        final String sourceName = provider + "-" + location;
        return sRepo.findBySourceName(sourceName);
    }

}
