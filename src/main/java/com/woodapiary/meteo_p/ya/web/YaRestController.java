/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ya.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woodapiary.meteo_p.ya.dao.YaConst;
import com.woodapiary.meteo_p.ya.dto.YaGetFactsResultDto;
import com.woodapiary.meteo_p.ya.service.YaMessageService;

@RestController
@RequestMapping("/api")
public class YaRestController {

    static Logger log = LoggerFactory.getLogger(YaRestController.class);

    @Autowired
    private YaMessageService messageService;

    @GetMapping("/get-ya-facts")
    public YaGetFactsResultDto getFacts(@RequestParam(name = "location", required = true) String location) {
        final YaGetFactsResultDto res = new YaGetFactsResultDto(messageService.getFacts(YaConst.providerYa, location));
        return res;
    }

}
