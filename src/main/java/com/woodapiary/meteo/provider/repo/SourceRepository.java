/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.provider.entity.Source;

public interface SourceRepository extends CrudRepository<Source, Long> {

    @Query(value = "select * from source as u where u.source_name = :source_name", nativeQuery = true)
    Source findBySourceName(@Param("source_name") String sourceName);

    @Query(value = "select * from source as u where u.provider = :provider", nativeQuery = true)
    List<Source> findByProvider(@Param("provider") String name);

}
