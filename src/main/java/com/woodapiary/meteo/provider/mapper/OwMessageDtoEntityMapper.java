/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.dto.ow.OwCurrentDto;
import com.woodapiary.meteo.provider.dto.ow.OwMessageDto;
import com.woodapiary.meteo.provider.dto.ow.OwWeatherDto;
import com.woodapiary.meteo.provider.entity.ow.OwFact;
import com.woodapiary.meteo.provider.entity.ow.OwMessage;
import com.woodapiary.meteo.provider.entity.ow.OwWeather;

@Component
public class OwMessageDtoEntityMapper {

    @Autowired
    private ModelMapper modelMapper;
    TypeMap<OwCurrentDto, OwFact> typeMapFactDtoToFact;
    TypeMap<OwFact, OwCurrentDto> typeMapFactDtoFromFact;

    //TODO source.getSource().stream().map(Human::getName).collect(Collectors.joining(","))
    Converter<List<String>, String> listOfStringToString = new AbstractConverter<>() {
        @Override
        protected String convert(List<String> source) {
            if (source == null) {
                return null;
            }
            final StringBuffer res = new StringBuffer();
            for (final String str : source) {
                res.append(str);
                res.append(';');
            }
            return res.toString();
        }
    };

    Converter<String, List<String>> listOfStringFromString = new AbstractConverter<>() {
        @Override
        protected List<String> convert(String source) {
            if (source == null) {
                return null;
            }
            final String[] ar = source.split(";");
            final List<String> res = Arrays.asList(ar);
            return res;
        }
    };

    @PostConstruct
    public void init() {
        /* modelMapper.createTypeMap(List.class, String.class);
        modelMapper.addConverter(listOfStringToString);
        modelMapper.createTypeMap(String.class, List.class);
        modelMapper.addConverter(listOfStringFromString);
        */
        typeMapFactDtoToFact = modelMapper.createTypeMap(OwCurrentDto.class, OwFact.class);
        //typeMapFactDtoToFact.addMappings(mapper -> mapper.using(stringToLocalTime).map(WsCurrentDto::getObservationTime, WsFact::setObservationTime));
        typeMapFactDtoFromFact = modelMapper.createTypeMap(OwFact.class, OwCurrentDto.class);
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
        final OwCurrentDto dto = modelMapper.map(entity, OwCurrentDto.class);
        return dto;
    }

    public List<OwWeather> weatherListDtoToWeatherList(final List<OwWeatherDto> dtoList) {
        final Type listType = new TypeToken<List<OwWeather>>() {
        }.getType();
        final List<OwWeather> entityList = modelMapper.map(dtoList, listType);
        return entityList;
    }

    public List<OwWeatherDto> weatherListDtoFromweatherList(final List<OwWeather> entityList) {
        final Type listType = new TypeToken<List<OwWeatherDto>>() {
        }.getType();
        final List<OwWeatherDto> dtoList = modelMapper.map(entityList, listType);
        return dtoList;
    }

}
