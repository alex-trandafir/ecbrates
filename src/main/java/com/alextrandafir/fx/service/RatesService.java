package com.alextrandafir.fx.service;

import com.alextrandafir.exceptions.NoRatesException;
import com.alextrandafir.fx.client.RatesClient;
import com.alextrandafir.fx.model.Day;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RatesService {

    @Autowired
    @Getter
    RatesClient ratesClient;

    public List<Day> getRates(LocalDate fromDay, LocalDate toDate) throws NoRatesException {
        List<Day> days = new ArrayList<>();

        if(toDate.isEqual(LocalDate.now())){
            days.addAll(getRates(RatesDay.TODAY));
        }

        if(fromDay.isBefore(LocalDate.now())){
            days.addAll(getRates(RatesDay.OLDER));
        }

        days = days.stream()
                .filter(day -> (day.getDate().isBefore(toDate) || day.getDate().isEqual(toDate)) && (day.getDate().isAfter(fromDay) || day.getDate().isEqual(fromDay)))
                .distinct()
                .sorted(Comparator.comparing(Day::getDate))
                .collect(Collectors.toList());

        return days;
    }

    @Cacheable(value = "ratesCache")
    public List<Day> getRates(RatesDay ratesDay) throws NoRatesException {

        log.info("Getting rates from server for ", ratesDay);
        if(ratesDay == RatesDay.TODAY){
            log.info("Called for today");
            return ratesClient.getRealtimeRate();
        }

        if(ratesDay == RatesDay.OLDER){
            log.info("Called for older");
             return ratesClient.getHistoricRates();
        }

        throw new NoRatesException();
    }

}
