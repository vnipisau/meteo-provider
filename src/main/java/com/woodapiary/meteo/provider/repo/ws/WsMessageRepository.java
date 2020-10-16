/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.repo.ws;

import org.springframework.data.repository.CrudRepository;

import com.woodapiary.meteo.provider.entity.ws.WsMessage;

public interface WsMessageRepository extends CrudRepository<WsMessage, Long> {

}
