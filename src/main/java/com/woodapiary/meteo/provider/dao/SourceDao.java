/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import com.woodapiary.meteo.provider.entity.Source;

public interface SourceDao {

    Source saveSource(Source entity);

    List<Source> findSourcesByProvider(String provider);

    void deleteAll();

    Source findBySourceName(String provider, String location);
}
