package com.alextrandafir.fx.jobs;

import com.alextrandafir.exceptions.NoRatesException;
import com.alextrandafir.fx.service.RatesDay;
import com.alextrandafir.fx.service.RatesService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

@Component
@Slf4j
public class RatesUpdateJob implements Job {

    @Autowired
    RatesService ratesService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext)  {
        log.info("Executing cache update");

        try {
            ratesService.getRates(RatesDay.TODAY);
            ratesService.getRates(RatesDay.OLDER);

        } catch (NoRatesException e) {
            log.error("Error running job");
        }
    }
}
