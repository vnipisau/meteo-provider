/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.misc.service;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.woodapiary.meteo_p.misc.dto.SourcesDto;

@Endpoint
public class SourceWsService {

    private static final String NAMESPACE_URI = "http://woodapiary.com/meteo-provider/ws";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSourcesByProvider")
    @ResponsePayload
    public SourcesDto getSourcesByProvider(@RequestPayload String provider) {
        return null;
    }
}
