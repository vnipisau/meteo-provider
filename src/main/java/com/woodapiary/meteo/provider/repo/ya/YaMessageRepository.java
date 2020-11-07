/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ya;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.woodapiary.meteo.provider.entity.ya.YaMessage;

public interface YaMessageRepository extends CrudRepository<YaMessage, Long> {

    @Query(value = " select message_id, max(modified) from ya_message ym where source_id=:source_id", nativeQuery = true)
    YaMessage findLastMessage(@Param("source_id") Long sourceId);
}
