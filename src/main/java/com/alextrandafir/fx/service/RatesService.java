package com.alextrandafir.fx.service;

import com.alextrandafir.fx.client.RatesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class RatesService {

    @Autowired
    RatesClient ratesClient;

    public String getDailyRate()  {
        String dailyRate = ratesClient.getDailyRate();
        return dailyRate;
    }

}
