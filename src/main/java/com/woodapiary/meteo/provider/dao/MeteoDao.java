/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import com.woodapiary.meteo.provider.entity.Source;

public interface MeteoDao {

    Source saveSource(Source entity);

    List<Source> findSourceByProvider(String provider);

    void deleteAll();

    Source findBySourceName(String provider, String location);
}
