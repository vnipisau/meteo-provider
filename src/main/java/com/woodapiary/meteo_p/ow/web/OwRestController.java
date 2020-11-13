/**
 * 2002-2020
 * woodapiary.com
 */
package com.woodapiary.meteo_p.ow.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woodapiary.meteo_p.ow.dao.OwConst;
import com.woodapiary.meteo_p.ow.dto.OwGetFactsResultDto;
import com.woodapiary.meteo_p.ow.service.OwMessageService;

@RestController
@RequestMapping("/api")
public class OwRestController {

    static Logger log = LoggerFactory.getLogger(OwRestController.class);

    @Autowired
    private OwMessageService messageService;

    //TODO
    /*
     * @Override public ResponseEntity<List<UserCalorieDailyEntry>> viewCalorie(@RequestBody CalorieViewTrackingRequest
     * trackCalorieRequest) { UserCalorieTrackingResponse userCalorieTrackingResponse =
     * this.calorieTrackingService.viewCalorie(trackCalorieRequest); return new
     * ResponseEntity<>(userCalorieTrackingResponse.getUserCalorieDailyTracking(), HttpStatus.OK); }
     */
    @GetMapping("/get-ow-facts")
    public OwGetFactsResultDto getFacts(@RequestParam(name = "location", required = true) String location) {
        final OwGetFactsResultDto res = new OwGetFactsResultDto(messageService.getFacts(OwConst.providerOw, location));
        return res;
    }

}
