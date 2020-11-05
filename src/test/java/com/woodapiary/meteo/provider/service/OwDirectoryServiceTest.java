/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwWeatherDto;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OwDirectoryServiceTest {

    @Autowired
    private OwDirectoryService requester;
    @Autowired
    OwDao dao;

    @Test
    public void test01() {
        assertNotNull(requester);
    }

    @Test
    public void test02() throws IOException {
        final List<OwWeatherDto> res = requester.readWeatherFromFile();
        assertTrue(res.size() > 0);
    }

    @Test
    public void test03() throws IOException {
        final List<OwWeather> res = requester.saveWeatherToDb();
        assertTrue(res.size() > 0);
        dao.deleteWeatherConditionCodes();
    }

}
