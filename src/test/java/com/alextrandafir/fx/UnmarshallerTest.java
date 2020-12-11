package com.alextrandafir.fx;

import com.alextrandafir.fx.model.Day;
import com.alextrandafir.fx.utils.Unmarshaller;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarshallerTest {

    static final String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<gesmes:Envelope xmlns:gesmes=\"http://www.gesmes.org/xml/2002-08-01\" xmlns=\"http://www.ecb.int/vocabulary/2002-08-01/eurofxref\">\n" +
            "\t<gesmes:subject>Reference rates</gesmes:subject>\n" +
            "\t<gesmes:Sender>\n" +
            "\t\t<gesmes:name>European Central Bank</gesmes:name>\n" +
            "\t</gesmes:Sender>\n" +
            "\t<Cube>\n" +
            "\t\t<Cube time='2020-12-04'>\n" +
            "\t\t\t<Cube currency='USD' rate='1.2159'/>\n" +
            "\t\t\t<Cube currency='JPY' rate='126.44'/>\n" +
            "\t\t\t<Cube currency='BGN' rate='1.9558'/>\n" +
            "\t\t\t<Cube currency='CZK' rate='26.518'/>\n" +
            "\t\t\t<Cube currency='DKK' rate='7.4429'/>\n" +
            "\t\t\t<Cube currency='GBP' rate='0.90282'/>\n" +
            "\t\t\t<Cube currency='HUF' rate='358.57'/>\n" +
            "\t\t\t<Cube currency='PLN' rate='4.4769'/>\n" +
            "\t\t\t<Cube currency='RON' rate='4.8725'/>\n" +
            "\t\t\t<Cube currency='SEK' rate='10.2578'/>\n" +
            "\t\t\t<Cube currency='CHF' rate='1.0822'/>\n" +
            "\t\t\t<Cube currency='ISK' rate='152.10'/>\n" +
            "\t\t\t<Cube currency='NOK' rate='10.6598'/>\n" +
            "\t\t\t<Cube currency='HRK' rate='7.5415'/>\n" +
            "\t\t\t<Cube currency='RUB' rate='90.0679'/>\n" +
            "\t\t\t<Cube currency='TRY' rate='9.4636'/>\n" +
            "\t\t\t<Cube currency='AUD' rate='1.6387'/>\n" +
            "\t\t\t<Cube currency='BRL' rate='6.2759'/>\n" +
            "\t\t\t<Cube currency='CAD' rate='1.5633'/>\n" +
            "\t\t\t<Cube currency='CNY' rate='7.9421'/>\n" +
            "\t\t\t<Cube currency='HKD' rate='9.4240'/>\n" +
            "\t\t\t<Cube currency='IDR' rate='17222.37'/>\n" +
            "\t\t\t<Cube currency='ILS' rate='3.9720'/>\n" +
            "\t\t\t<Cube currency='INR' rate='89.6755'/>\n" +
            "\t\t\t<Cube currency='KRW' rate='1320.60'/>\n" +
            "\t\t\t<Cube currency='MXN' rate='24.1091'/>\n" +
            "\t\t\t<Cube currency='MYR' rate='4.9366'/>\n" +
            "\t\t\t<Cube currency='NZD' rate='1.7254'/>\n" +
            "\t\t\t<Cube currency='PHP' rate='58.430'/>\n" +
            "\t\t\t<Cube currency='SGD' rate='1.6206'/>\n" +
            "\t\t\t<Cube currency='THB' rate='36.672'/>\n" +
            "\t\t\t<Cube currency='ZAR' rate='18.4674'/>\n" +
            "\t\t</Cube>\n" +
            "\t</Cube>\n" +
            "</gesmes:Envelope>";

    @Test
    public void testUnmarshaller() throws IOException, SAXException, ParserConfigurationException {
        Unmarshaller unmarshaller = new Unmarshaller();
        List<Day> days = unmarshaller.unmarshal(xmlString);
        assertEquals(1, days.size());
        assertEquals(32, days.get(0).getRates().size());
    }

}
