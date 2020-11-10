/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.provider.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woodapiary.meteo.provider.dto.ws.WsGetFactsResultDto;
import com.woodapiary.meteo.provider.service.ProviderConst;
import com.woodapiary.meteo.provider.service.WsMessageService;

@RestController
@RequestMapping("/api")
public class WsRestController {

    static Logger log = LoggerFactory.getLogger(WsRestController.class);

    @Autowired
    private WsMessageService messageService;

    @GetMapping("/get-ws-facts")
    public WsGetFactsResultDto getFacts(@RequestParam(name = "location", required = true) String location) {
        final WsGetFactsResultDto res = new WsGetFactsResultDto(messageService.getFacts(ProviderConst.providerWs, location));
        return res;
    }

}
