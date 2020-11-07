/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ow;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.provider.entity.ow.OwMessage;

public interface OwMessageRepository extends CrudRepository<OwMessage, Long> {
    @Query(value = " select message_id, max(modified) from ow_message ow where source_id=:source_id", nativeQuery = true)
    OwMessage findLastMessage(@Param("source_id") Long sourceId);
}
