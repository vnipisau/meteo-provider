/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.dto.ya.YaFactDto;
import com.woodapiary.meteo.provider.dto.ya.YaForecastDto;
import com.woodapiary.meteo.provider.dto.ya.YaMessageDto;
import com.woodapiary.meteo.provider.dto.ya.YaPartDto;
import com.woodapiary.meteo.provider.entity.ya.YaFact;
import com.woodapiary.meteo.provider.entity.ya.YaForecast;
import com.woodapiary.meteo.provider.entity.ya.YaMessage;
import com.woodapiary.meteo.provider.entity.ya.YaPart;

@Component
public class YaMessageDtoEntityMapper {

    @Autowired
    private ModelMapper modelMapper;

    public YaMessage messageDtoToMessage(final YaMessageDto dto) {
        final YaMessage entity = modelMapper.map(dto, YaMessage.class);
        return entity;
    }

    public YaFact factDtoToFact(final YaFactDto dto) {
        final YaFact entity = modelMapper.map(dto, YaFact.class);
        return entity;
    }

    public YaForecast forecastDtoToForecast(final YaForecastDto dto) {
        final YaForecast entity = modelMapper.map(dto, YaForecast.class);
        return entity;
    }

    public List<YaPart> partListDtoToPartList(final List<YaPartDto> dtoList) {
        final Type listType = new TypeToken<List<YaPart>>() {
        }.getType();
        final List<YaPart> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public YaMessageDto messageDtoFromMessage(final YaMessage entity) {
        final YaMessageDto dto = modelMapper.map(entity, YaMessageDto.class);
        return dto;
    }

    public YaFactDto factDtoFromFact(final YaFact entity) {
        final YaFactDto dto = modelMapper.map(entity, YaFactDto.class);
        return dto;
    }

    public YaForecastDto forecastDtoFromForecast(final YaForecast entity) {
        final YaForecastDto dto = modelMapper.map(entity, YaForecastDto.class);
        return dto;
    }

    public List<YaPartDto> partListDtoFromPartList(final List<YaPart> entityList) {
        final Type listType = new TypeToken<List<YaPartDto>>() {
        }.getType();
        final List<YaPartDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

}
