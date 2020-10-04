/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.ya.dto.FactDto;
import com.woodapiary.meteo.ya.dto.ForecastDto;
import com.woodapiary.meteo.ya.dto.MessageDto;
import com.woodapiary.meteo.ya.dto.PartDto;
import com.woodapiary.meteo.ya.entity.Fact;
import com.woodapiary.meteo.ya.entity.Forecast;
import com.woodapiary.meteo.ya.entity.Message;
import com.woodapiary.meteo.ya.entity.Part;

@Component
public class YaMessageDtoEntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Message messageDtoToMessage(final MessageDto dto) {
        final Message entity = modelMapper.map(dto, Message.class);
        return entity;
    }

    public Fact factDtoToFact(final FactDto dto) {
        final Fact entity = modelMapper.map(dto, Fact.class);
        return entity;
    }

    public Forecast forecastDtoToForecast(final ForecastDto dto) {
        final Forecast entity = modelMapper.map(dto, Forecast.class);
        return entity;
    }

    public List<Part> partListDtoToPartList(final List<PartDto> dtoList) {
        final Type listType = new TypeToken<List<Part>>() {
        }.getType();
        final List<Part> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public MessageDto messageDtoFromMessage(final Message entity) {
        final MessageDto dto = modelMapper.map(entity, MessageDto.class);
        return dto;
    }

    public FactDto factDtoFromFact(final Fact entity) {
        final FactDto dto = modelMapper.map(entity, FactDto.class);
        return dto;
    }

    public ForecastDto forecastDtoFromForecast(final Forecast entity) {
        final ForecastDto dto = modelMapper.map(entity, ForecastDto.class);
        return dto;
    }

    public List<PartDto> partListDtoFromPartList(final List<Part> entityList) {
        final Type listType = new TypeToken<List<PartDto>>() {
        }.getType();
        final List<PartDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

}
