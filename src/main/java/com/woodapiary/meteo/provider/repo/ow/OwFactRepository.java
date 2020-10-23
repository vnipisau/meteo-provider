/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.provider.entity.ow.OwFact;

public interface OwFactRepository extends CrudRepository<OwFact, Long> {
    @Query(value = "select * from ow_fact", nativeQuery = true)
    List<OwFact> findBySource(@Param("source_id") String sourceId);
}
