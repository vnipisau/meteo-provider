/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo_p.ws.entities.WsFact;

public interface WsFactRepository extends CrudRepository<WsFact, Long> {
    @Query(value = "select * from ws_fact", nativeQuery = true)
    List<WsFact> findBySource(@Param("source_id") Long sourceId);
}
