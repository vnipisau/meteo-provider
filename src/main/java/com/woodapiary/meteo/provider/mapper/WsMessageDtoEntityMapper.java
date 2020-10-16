/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.mapper;

import org.modelmapper.ModelMapper;
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

    public WsMessage messageDtoToMessage(final WsMessageDto dto) {
        final WsMessage entity = modelMapper.map(dto, WsMessage.class);
        return entity;
    }

    public WsFact factDtoToFact(final WsCurrentDto dto) {
        dto.setObservationTime(null);//TODO
        final WsFact entity = modelMapper.map(dto, WsFact.class);
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
