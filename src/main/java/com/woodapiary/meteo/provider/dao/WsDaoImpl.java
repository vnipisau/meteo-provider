/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.repo.ws.WsFactRepository;
import com.woodapiary.meteo.provider.repo.ws.WsMessageRepository;

@Repository
public class WsDaoImpl implements WsDao {

    @Autowired
    private WsMessageRepository messageRepo;
    @Autowired
    private WsFactRepository factRepo;
    @Autowired
    private SourceDao meteoDao;

    @Override
    @Transactional
    public WsMessage saveMessage(WsMessage entity, String provider, String location) {
        final Source source = meteoDao.findBySourceName(provider, location);
        entity.setSource(source);
        messageRepo.save(entity);
        if (entity.getFact() != null) {
            entity.getFact().setMessage(entity);
            factRepo.save(entity.getFact());
        }
        return messageRepo.save(entity);
    }

    @Override
    @Transactional
    public void deleteAllMessages() {
        factRepo.deleteAll();
        messageRepo.deleteAll();
    }

    @Override
    @Transactional
    public List<WsFact> findFacts(String provider, String location) {
        final Source src = meteoDao.findBySourceName(provider, location);
        return factRepo.findBySource(src.getSourceId());
    }

    @Override
    public long countMessages() {
        return messageRepo.count();
    }

    @Override
    public long countFacts() {
        return factRepo.count();
    }

    @Override
    @Transactional
    public WsMessage findLastMessage(String provider, String location) {
        final Source src = meteoDao.findBySourceName(provider, location);
        return messageRepo.findLastMessage(src.getSourceId());
    }

    @Override
    public List<WsMessage> saveMessages(List<WsMessage> messages, String provider, String location) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<WsMessage> findMessages(String provider, String location) {
        // TODO Auto-generated method stub
        return null;
    }

}
