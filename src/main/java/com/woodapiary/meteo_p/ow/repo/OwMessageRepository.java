/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo_p.ow.entities.OwMessage;

public interface OwMessageRepository extends CrudRepository<OwMessage, Long> {
    @Query(value = " select message_id, max(modified) from ow_message ow where source_id=:source_id", nativeQuery = true)
    OwMessage findLastMessage(@Param("source_id") Long sourceId);
}
