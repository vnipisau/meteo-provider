/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
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
    TypeMap<YaForecastDto, YaForecast> typeMapForecastDtoToForecast;
    TypeMap<YaForecast, YaForecastDto> typeMapForecastDtoFromForecast;

    Converter<List<YaPart>, List<YaPartDto>> partListDtoFromPartList = new AbstractConverter<>() {
        @Override
        protected List<YaPartDto> convert(List<YaPart> source) {
            if (source == null) {
                return null;
            }
            return partListDtoFromPartList(source);
        }
    };

    Converter<List<YaPartDto>, List<YaPart>> partListDtoToPartList = new AbstractConverter<>() {
        @Override
        protected List<YaPart> convert(List<YaPartDto> source) {
            if (source == null) {
                return null;
            }
            return partListDtoToPartList(source);
        }
    };

    @PostConstruct
    public void init() {
        typeMapForecastDtoToForecast = modelMapper.createTypeMap(YaForecastDto.class, YaForecast.class);
        typeMapForecastDtoToForecast.addMappings(mapper -> mapper.using(partListDtoToPartList).map(YaForecastDto::getParts, YaForecast::setParts));
        typeMapForecastDtoFromForecast = modelMapper.createTypeMap(YaForecast.class, YaForecastDto.class);
        typeMapForecastDtoFromForecast.addMappings(mapper -> mapper.using(partListDtoFromPartList).map(YaForecast::getParts, YaForecastDto::setParts));
    }

    public YaMessage messageDtoToMessage(final YaMessageDto dto) {
        final YaMessage entity = modelMapper.map(dto, YaMessage.class);
        return entity;
    }

    public YaFact factDtoToFact(final YaFactDto dto) {
        final YaFact entity = modelMapper.map(dto, YaFact.class);
        return entity;
    }

    public YaForecast forecastDtoToForecast(final YaForecastDto dto) {
        final YaForecast entity = typeMapForecastDtoToForecast.map(dto);
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
        final YaForecastDto dto = typeMapForecastDtoFromForecast.map(entity);
        return dto;
    }

    public List<YaPartDto> partListDtoFromPartList(final List<YaPart> entityList) {
        final Type listType = new TypeToken<List<YaPartDto>>() {
        }.getType();
        final List<YaPartDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

    public List<YaMessage> messagesDtoToMessages(List<YaMessageDto> dto) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<YaMessageDto> messagesDtoFromMessages(List<YaMessage> entity) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<YaFactDto> factsDtoFromFacts(List<YaFact> entityList) {
        // FIXME make via mapper
        final List<YaFactDto> res = new ArrayList<>();
        for (final YaFact entity : entityList) {
            res.add(factDtoFromFact(entity));
        }
        return res;
    }

}
