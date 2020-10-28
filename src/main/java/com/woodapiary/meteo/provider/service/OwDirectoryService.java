/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.woodapiary.meteo.provider.dao.OwDao;
import com.woodapiary.meteo.provider.dto.ow.OwWeatherDto;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;
import com.woodapiary.meteo.provider.mapper.OwMessageDtoEntityMapper;

@Service
public class OwDirectoryService {

    static Logger log = LoggerFactory.getLogger(OwDirectoryService.class);
    public static final String provider = "openweathermap";

    @Autowired
    OwDao dao;
    @Autowired
    OwMessageDtoEntityMapper mapper;
    private final CsvMapper mapperCsv = new CsvMapper();

    public List<OwWeatherDto> readFromFile() throws IOException {
        final File file = ResourceUtils.getFile("classpath:data/ow_condition_codes.csv");
        try (final FileInputStream fis = new FileInputStream(file)) {
            final CsvSchema schema = mapperCsv.schemaFor(OwWeatherDto.class).withHeader().withColumnReordering(true).withColumnSeparator('\t');
            final ObjectReader reader = mapperCsv.readerFor(OwWeatherDto.class).with(schema);
            return reader.<OwWeatherDto>readValues(fis).readAll();
        }
    }

    public List<OwWeather> saveToDb() throws IOException {
        final List<OwWeather> entityList = mapper.weatherListDtoToWeatherList(readFromFile());
        dao.saveWeatherConditionCodes(entityList);
        log.info("save openweather condition codes to db - ok");
        return entityList;
    }

}
