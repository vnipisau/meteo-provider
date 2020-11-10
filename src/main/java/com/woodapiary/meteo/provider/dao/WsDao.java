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

    WsMessage saveMessage(WsMessage message, String provider, String location);

    void deleteAllMessages();

    List<WsFact> findFacts(String provider, String location);

    long countMessages();

    long countFacts();

    WsMessage findLastMessage(String provider, String location);

    List<WsMessage> saveMessages(List<WsMessage> messages, String provider, String location);

    List<WsMessage> findMessages(String provider, String location);
}
