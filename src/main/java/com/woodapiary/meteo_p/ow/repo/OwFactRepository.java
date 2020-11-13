/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo_p.ow.entities.OwFact;

public interface OwFactRepository extends CrudRepository<OwFact, Long> {
    @Query(value = "select * from ow_fact", nativeQuery = true)
    List<OwFact> findBySource(@Param("source_id") Long sourceId);
}
