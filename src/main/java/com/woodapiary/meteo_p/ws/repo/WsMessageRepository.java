/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ws.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo_p.ws.entities.WsMessage;

public interface WsMessageRepository extends CrudRepository<WsMessage, Long> {
    @Query(value = " select message_id, max(modified) from ws_message ws where source_id=:source_id", nativeQuery = true)
    WsMessage findLastMessage(@Param("source_id") Long sourceId);
}
