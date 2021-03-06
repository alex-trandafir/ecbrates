package com.alextrandafir.fx.utils;

import com.alextrandafir.fx.model.Day;
import com.alextrandafir.fx.model.Rate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
    Used for parsing XML input into usable POJOs
 */
@Slf4j
@Component
public class Unmarshaller {

    /*
        Parsing XML
        Ugly AF, still easier than XSD client w/ JAXB Unmarshalling
     */
    public List<Day> unmarshal (String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
        Document document = builder.parse(inputStream);
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("Cube");
        Node rootCube = nList.item(0);
        List<Day> days = new ArrayList<>();
        Node cubeDay = rootCube.getFirstChild();
        while(cubeDay != null){
            if (cubeDay.getNodeType() == Node.ELEMENT_NODE) {
                NodeList rates = cubeDay.getChildNodes();
                Element dayElement = (Element) cubeDay;
                if(dayElement.hasAttribute("time")) {
                    Day.DayBuilder dayBuilder = Day.builder();
                    String time = dayElement.getAttribute("time");
                    dayBuilder.date(LocalDate.parse(time));
                    List dayRates = new ArrayList();
                    for (int j = 0; j < rates.getLength(); j++) {
                        Node cubeRate = rates.item(j);
                        if (cubeRate.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) cubeRate;
                            if (eElement.hasAttribute("currency") && eElement.hasAttribute("rate")) {
                                String currency = eElement.getAttribute("currency");
                                String rateElement = eElement.getAttribute("rate");
                                Rate rate = Rate.builder().currency(currency)
                                        .rate(Double.parseDouble(rateElement))
                                        .build();
                                dayRates.add(rate);
                            }
                        }
                    }
                    if(dayRates.size()!=0) {
                        dayBuilder.rates(dayRates);
                        days.add(dayBuilder.build());
                    }
                }
            }
            cubeDay= cubeDay.getNextSibling();
        }
        return days;
    }
}
