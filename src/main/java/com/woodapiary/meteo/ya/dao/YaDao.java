/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.woodapiary.meteo.ya.entity.Fact;
import com.woodapiary.meteo.ya.entity.Forecast;
import com.woodapiary.meteo.ya.entity.Message;
import com.woodapiary.meteo.ya.entity.Part;
import com.woodapiary.meteo.ya.entity.Source;

@Repository
public interface YaDao {

    Message saveMessage(Message Message, Source source);

    Fact saveFact(Message Message, Fact fact);

    Forecast saveForecast(Message Message, Forecast forecast, List<Part> parts);

    void deleteAllMessages();

}
