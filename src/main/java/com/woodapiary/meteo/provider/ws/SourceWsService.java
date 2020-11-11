package com.woodapiary.meteo.provider.ws;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.woodapiary.meteo.provider.dto.src.SourcesDto;

@Endpoint
public class SourceWsService {

    private static final String NAMESPACE_URI = "http://woodapiary.com/meteo-provider/ws";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSourcesByProvider")
    @ResponsePayload
    public SourcesDto getSourcesByProvider(@RequestPayload String provider) {
        return null;
    }
}
