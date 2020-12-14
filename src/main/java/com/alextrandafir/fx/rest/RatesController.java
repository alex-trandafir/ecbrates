package com.alextrandafir.fx.rest;

import com.alextrandafir.exceptions.NoRatesException;
import com.alextrandafir.fx.model.Day;
import com.alextrandafir.fx.service.RatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class RatesController {

    @Autowired
    RatesService ratesService;

    @RequestMapping(value="/rates", method= RequestMethod.GET)
    public List<Day> getRates(@RequestParam(value = "fromDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
                              @RequestParam(value = "toDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate)  {
        List<Day> days;
        try {
            days = ratesService.getRates(fromDate, toDate);
        } catch (NoRatesException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found", e);
        }
        return days;
    }

}
