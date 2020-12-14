package com.alextrandafir.fx;

import com.alextrandafir.fx.model.Day;
import com.alextrandafir.fx.utils.ResourceReader;
import com.alextrandafir.fx.utils.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.xml.sax.SAXException;

import org.springframework.core.io.Resource;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class UnmarshallerTest {

    @Value("classpath:eurofxref-daily.xml")
    private Resource eurofxrefDailyResource;

    @Value("classpath:eurofxref-hist-90d.xml")
    private Resource eurofxrefHistResource;

    @Test
    public void testUnmarshallerForDailyRate() throws IOException, SAXException, ParserConfigurationException {
        Unmarshaller unmarshaller = new Unmarshaller();
        String stringXml = ResourceReader.asString(eurofxrefDailyResource);
        List<Day> days = unmarshaller.unmarshal(stringXml);
        assertEquals(1, days.size());
        assertEquals(32, days.get(0).getRates().size());
    }

    @Test
    public void testUnmarshallerForHistoryRate() throws IOException, SAXException, ParserConfigurationException {
        Unmarshaller unmarshaller = new Unmarshaller();
        String stringXml = ResourceReader.asString(eurofxrefHistResource);
        List<Day> days = unmarshaller.unmarshal(stringXml);
        assertEquals(65, days.size());

        assertEquals(32, days.get(0).getRates().size());

        Integer totalRates = days.stream()
                .mapToInt(day -> day.getRates().size())
                .sum();
        assertEquals(32 * 65, totalRates);
        assertEquals(32, days.get(0).getRates().size());
    }

}
