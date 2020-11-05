/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.entity.Source;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;
import com.woodapiary.meteo.provider.repo.ws.WsFactRepository;
import com.woodapiary.meteo.provider.repo.ws.WsMessageRepository;

@Component
public class WsDaoImpl implements WsDao {

    @Autowired
    private WsMessageRepository messageRepo;
    @Autowired
    private WsFactRepository factRepo;

    @Override
    @Transactional
    public WsMessage saveMessage(WsMessage entity, Source source) {
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
    public List<WsFact> findBySource(String sourceId) {
        return factRepo.findBySource(sourceId);
    }

    @Override
    public long countMessages() {
        return messageRepo.count();
    }

    @Override
    public long countFacts() {
        return factRepo.count();
    }

}
