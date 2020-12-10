package com.alextrandafir.fx.service;

import com.alextrandafir.fx.client.RatesClient;
import com.alextrandafir.fx.ws.CubeType;
import com.alextrandafir.fx.ws.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Service
public class RatesService {

    @Autowired
    RatesClient ratesClient;

    public String getDailyRate() throws JAXBException {
        String dailyRate = ratesClient.getDailyRate();
        return dailyRate;
    }

}
