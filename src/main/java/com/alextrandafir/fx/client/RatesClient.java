package com.alextrandafir.fx.client;

import com.alextrandafir.exceptions.NoRatesException;
import com.alextrandafir.fx.model.Day;
import com.alextrandafir.fx.utils.Unmarshaller;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class RatesClient {

    public static final String EURO_FX_DAILY = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    public static final String EURO_FX_HISTORY ="https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml";

    @Autowired
    @Setter
    private RestTemplate restTemplate;

    @Autowired
    Unmarshaller unmarshaller;

    public List<Day> getRealtimeRate() throws NoRatesException {
        String xmlResponse = restTemplate.getForObject(EURO_FX_DAILY, String.class);
        List<Day> days;
        try {
            days = unmarshaller.unmarshal(xmlResponse);
        } catch (ParserConfigurationException|IOException|SAXException e) {
            log.error("Error unmarshaling data", e);
            throw new NoRatesException();
        }
        return days;
    }

    public List<Day> getHistoricRates() throws NoRatesException {
        String xmlResponse = restTemplate.getForObject(EURO_FX_HISTORY, String.class);
        List<Day> days;
        try {
            days = unmarshaller.unmarshal(xmlResponse);
        } catch (ParserConfigurationException|IOException|SAXException e) {
            log.error("Error getting data from Rates Service", e);
            throw new NoRatesException();
        }
        return days;
    }


}
