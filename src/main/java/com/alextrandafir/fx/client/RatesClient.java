package com.alextrandafir.fx.client;

import com.alextrandafir.fx.ws.CubeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Service
public class RatesClient extends WebServiceGatewaySupport {

    public static final String EURO_FX_DAILY = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";

    @Autowired
    private RestTemplate restTemplate;

    public String getDailyRate() {
        String response = restTemplate.getForObject(EURO_FX_DAILY, String.class);
        return response;
    }


}
