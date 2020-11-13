/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.misc.dao;

import java.util.List;

import com.woodapiary.meteo_p.misc.entities.Source;

public interface SourceDao {

    Source saveSource(Source entity);

    List<Source> findSourcesByProvider(String provider);

    void deleteAll();

    Source findBySourceName(String provider, String location);
}
