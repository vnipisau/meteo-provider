/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ya;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.provider.entity.ya.YaFact;

public interface YaFactRepository extends CrudRepository<YaFact, Long> {
    @Query(value = "select * from ya_fact", nativeQuery = true)
    List<YaFact> findBySource(@Param("source_id") Long sourceId);
}
