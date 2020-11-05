/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwWeatherDto;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;
import com.woodapiary.meteo.provider.mapper.OwMessageDtoEntityMapper;
import com.woodapiary.meteo.provider.misc.ObjectSerializator;

@Service
public class OwDirectoryService {

    static Logger log = LoggerFactory.getLogger(OwDirectoryService.class);
    public static final String provider = "openweathermap";

    @Autowired
    OwDao dao;
    @Autowired
    OwMessageDtoEntityMapper mapper;
    @Autowired
    private ResourceLoader resourceLoader;

    public List<OwWeatherDto> readWeatherFromFile() throws IOException {
        final Resource resource = resourceLoader.getResource("classpath:data/ow_condition_codes.csv");
        return new ObjectSerializator<OwWeatherDto>().readCsvFromFileV2(resource.getInputStream(), OwWeatherDto.class);
    }

    public List<OwWeather> saveWeatherToDb() throws IOException {
        final List<OwWeather> entityList = mapper.weatherListDtoToWeatherList(readWeatherFromFile());
        dao.saveWeatherConditionCodes(entityList);
        log.info("save openweather condition codes to db - ok");
        return entityList;
    }

    public void startup() throws IOException {
        //TODO check when table is empty
        saveWeatherToDb();
    }

}
