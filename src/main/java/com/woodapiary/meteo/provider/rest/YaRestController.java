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
import org.springframework.web.bind.annotation.RestController;

import com.woodapiary.meteo.provider.dto.ya.YaGetFactsResultDto;
import com.woodapiary.meteo.provider.service.YaMessageService;

@RestController
@RequestMapping("/api")
public class YaRestController {

    static Logger log = LoggerFactory.getLogger(YaRestController.class);

    @Autowired
    private YaMessageService messageService;

    @GetMapping("/get-ya-facts")
    public YaGetFactsResultDto getFacts() {
        final String sourceName = "q";
        final YaGetFactsResultDto res = new YaGetFactsResultDto(messageService.getFacts(sourceName));
        return res;
    }

}
