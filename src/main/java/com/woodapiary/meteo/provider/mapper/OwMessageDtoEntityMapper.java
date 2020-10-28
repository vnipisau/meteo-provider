/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.dto.ow.OwAlertDto;
import com.woodapiary.meteo.provider.dto.ow.OwCurrentDto;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ow.OwRainDto;
import com.woodapiary.meteo.provider.dto.ow.OwWeatherDto;
import com.woodapiary.meteo.provider.entity.ow.OwAlert;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;

@Component
public class OwMessageDtoEntityMapper {

    @Autowired
    private ModelMapper modelMapper;
    TypeMap<OwCurrentDto, OwFact> typeMapFactDtoToFact;
    TypeMap<OwFact, OwCurrentDto> typeMapFactDtoFromFact;

    Converter<OwRainDto, Double> rainToDouble = new AbstractConverter<>() {
        @Override
        protected Double convert(OwRainDto source) {
            if (source == null) {
                return null;
            }
            return source.getM1h();
        }
    };

    Converter<Double, OwRainDto> rainFromDouble = new AbstractConverter<>() {
        @Override
        protected OwRainDto convert(Double source) {
            if (source == null) {
                return null;
            }
            final OwRainDto res = new OwRainDto();
            res.set1h(source);
            return res;
        }
    };

    /*
    Converter<List<OwWeather>, List<OwWeatherDto>> weatherListDtoFromWeatherList = new AbstractConverter<>() {
        @Override
        protected List<OwWeatherDto> convert(List<OwWeather> source) {
            if (source == null) {
                return null;
            }
            return weatherListDtoFromWeatherList(source);
        }
    };
    */
    @PostConstruct
    public void init() {
        typeMapFactDtoToFact = modelMapper.createTypeMap(OwCurrentDto.class, OwFact.class);
        typeMapFactDtoToFact.addMappings(mapper -> mapper.using(rainToDouble).map(OwCurrentDto::getRain, OwFact::setRain1h));
        typeMapFactDtoToFact.addMappings(mapper -> mapper.using(rainToDouble).map(OwCurrentDto::getSnow, OwFact::setSnow1h));
        typeMapFactDtoFromFact = modelMapper.createTypeMap(OwFact.class, OwCurrentDto.class);
        typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(rainFromDouble).map(OwFact::getRain1h, OwCurrentDto::setRain));
        typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(rainFromDouble).map(OwFact::getSnow1h, OwCurrentDto::setSnow));
        //typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(weatherListDtoFromWeatherList).map(OwFact::getWeather, OwCurrentDto::setWeather));
    }

    public OwMessage messageDtoToMessage(final OwMessageDto dto) {
        final OwMessage entity = modelMapper.map(dto, OwMessage.class);
        return entity;
    }

    public OwFact factDtoToFact(final OwCurrentDto dto) {
        final OwFact entity = typeMapFactDtoToFact.map(dto);
        return entity;
    }

    public OwMessageDto messageDtoFromMessage(final OwMessage entity) {
        final OwMessageDto dto = modelMapper.map(entity, OwMessageDto.class);
        return dto;
    }

    public OwCurrentDto factDtoFromFact(final OwFact entity) {
        final OwCurrentDto dto = typeMapFactDtoFromFact.map(entity);
        return dto;
    }

    public List<OwWeather> weatherListDtoToWeatherList(final List<OwWeatherDto> dtoList) {
        final Type listType = new TypeToken<List<OwWeather>>() {
        }.getType();
        final List<OwWeather> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwWeatherDto> weatherListDtoFromWeatherList(final List<OwWeather> entityList) {
        final Type listType = new TypeToken<List<OwWeatherDto>>() {
        }.getType();
        final List<OwWeatherDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

    public List<OwAlert> alertListDtoToAlertList(List<OwAlertDto> dtoList) {
        final Type listType = new TypeToken<List<OwAlert>>() {
        }.getType();
        final List<OwAlert> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwAlertDto> alertListDtoFromAlertList(List<OwAlert> entityList) {
        final Type listType = new TypeToken<List<OwAlertDto>>() {
        }.getType();
        final List<OwAlertDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

}
