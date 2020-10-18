/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.woodapiary.meteo.provider.dto.ws.WsCurrentDto;
import com.woodapiary.meteo.provider.dto.ws.WsMessageDto;
import com.woodapiary.meteo.provider.entity.ws.WsFact;
import com.woodapiary.meteo.provider.entity.ws.WsMessage;

@Component
public class WsMessageDtoEntityMapper {

    @Autowired
    private ModelMapper modelMapper;
    TypeMap<WsCurrentDto, WsFact> typeMapFactDtoToFact;
    TypeMap<WsFact, WsCurrentDto> typeMapFactDtoFromFact;

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

    Converter<String, LocalTime> stringToLocalTime = new AbstractConverter<>() {
        @Override
        protected LocalTime convert(String source) {
            if (source == null) {
                return null;
            }
            final LocalTime localDate = LocalTime.parse(source, DateTimeFormatter.ofPattern("hh:mm a"));
            return localDate;
        }
    };

    Converter<LocalTime, String> stringFromLocalTime = new AbstractConverter<>() {
        @Override
        protected String convert(LocalTime source) {
            if (source == null) {
                return null;
            }
            final String localDate = source.format(DateTimeFormatter.ofPattern("hh:mm a"));
            return localDate;
        }
    };

    @PostConstruct
    public void init() {
        modelMapper.createTypeMap(List.class, String.class);
        modelMapper.addConverter(listOfStringToString);
        modelMapper.createTypeMap(String.class, List.class);
        modelMapper.addConverter(listOfStringFromString);
        typeMapFactDtoToFact = modelMapper.createTypeMap(WsCurrentDto.class, WsFact.class);
        typeMapFactDtoToFact.addMappings(mapper -> mapper.using(stringToLocalTime).map(WsCurrentDto::getObservationTime, WsFact::setObservationTime));
        typeMapFactDtoFromFact = modelMapper.createTypeMap(WsFact.class, WsCurrentDto.class);
        typeMapFactDtoFromFact.addMappings(mapper -> mapper.using(stringFromLocalTime).map(WsFact::getObservationTime, WsCurrentDto::setObservationTime));
    }

    public WsMessage messageDtoToMessage(final WsMessageDto dto) {
        final WsMessage entity = modelMapper.map(dto, WsMessage.class);
        return entity;
    }

    public WsFact factDtoToFact(final WsCurrentDto dto) {
        final WsFact entity = typeMapFactDtoToFact.map(dto);
        return entity;
    }

    public WsMessageDto messageDtoFromMessage(final WsMessage entity) {
        final WsMessageDto dto = modelMapper.map(entity, WsMessageDto.class);
        return dto;
    }

    public WsCurrentDto factDtoFromFact(final WsFact entity) {
        final WsCurrentDto dto = modelMapper.map(entity, WsCurrentDto.class);
        return dto;
    }

}
