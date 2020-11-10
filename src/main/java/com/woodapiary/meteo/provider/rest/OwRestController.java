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

import com.woodapiary.meteo.provider.dto.ow.OwGetFactsResultDto;
import com.woodapiary.meteo.provider.service.OwMessageService;
import com.woodapiary.meteo.provider.service.ProviderConst;

@RestController
@RequestMapping("/api")
public class OwRestController {

    static Logger log = LoggerFactory.getLogger(OwRestController.class);

    @Autowired
    private OwMessageService messageService;

    @GetMapping("/get-ow-facts")
    public OwGetFactsResultDto getFacts(@RequestParam(name = "location", required = true) String location) {
        final OwGetFactsResultDto res = new OwGetFactsResultDto(messageService.getFacts(ProviderConst.providerOw, location));
        return res;
    }

}
