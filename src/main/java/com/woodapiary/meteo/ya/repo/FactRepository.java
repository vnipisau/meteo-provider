/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.ya.entity.Fact;

public interface FactRepository extends CrudRepository<Fact, Long> {
    @Query(value = "select * from fact", nativeQuery = true)
    List<Fact> findBySource(@Param("source_id") String sourceId);
}
