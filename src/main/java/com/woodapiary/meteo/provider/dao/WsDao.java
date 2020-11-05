/**
 * 2002-2020
 * woodapiary.com
 */
/**
  * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;

@Repository
public interface WsDao {

    WsMessage saveMessage(WsMessage message, Source source);

    void deleteAllMessages();

    List<WsFact> findBySource(String sourceId);

    long countMessages();

    long countFacts();
}
