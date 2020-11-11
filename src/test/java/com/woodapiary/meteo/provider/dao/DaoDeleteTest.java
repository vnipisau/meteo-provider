/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Commit
public class DaoDeleteTest {

    static Logger log = LoggerFactory.getLogger(DaoDeleteTest.class);

    @Autowired
    private YaDao daoYa;
    @Autowired
    private WsDao daoWs;
    @Autowired
    private SourceDao sRepo;
    @Autowired
    private OwDao daoOw;

    @Test
    public void test00() {
        clear();
    }

    void clear() {
        daoYa.deleteAllMessages();
        daoWs.deleteAllMessages();
        daoOw.deleteAllMessages();
        daoOw.deleteWeatherConditionCodes();
        sRepo.deleteAll();
    }

}
