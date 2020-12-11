package com.alextrandafir.fx.rest;

import com.alextrandafir.fx.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class RatesController {

    @Autowired
    RatesService ratesService;

    @RequestMapping(value="/rates", method= RequestMethod.GET)
    public String getRates(@PathVariable(value = "fromDate") Date fromDate)  {
        return ratesService.getDailyRate();
    }

}
