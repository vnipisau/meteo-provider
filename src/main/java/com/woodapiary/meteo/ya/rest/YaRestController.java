/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo.ya.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.woodapiary.meteo.ya.dto.GetFactsResultDto;
import com.woodapiary.meteo.ya.service.YaMessageService;

@RestController
@RequestMapping("/api")
public class YaRestController {

    static Logger log = LoggerFactory.getLogger(YaRestController.class);

    @Autowired
    private YaMessageService messageService;

    @GetMapping("/getfacts")
    public GetFactsResultDto getFacts() {
        final String sourceId = "q";
        final GetFactsResultDto res = new GetFactsResultDto(messageService.getFacts(sourceId));
        return res;
    }

}
