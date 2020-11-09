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

import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;

public interface WsDao {

    WsMessage saveMessage(WsMessage message, String sourceName);

    void deleteAllMessages();

    List<WsFact> findBySource(String sourceName);

    long countMessages();

    long countFacts();

    WsMessage findLastMessage(String sourceName);
}
