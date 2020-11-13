/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo_p.ya.entities.YaFact;

public interface YaFactRepository extends CrudRepository<YaFact, Long> {
    @Query(value = "select * from ya_fact", nativeQuery = true)
    List<YaFact> findBySource(@Param("source_id") Long sourceId);
}
