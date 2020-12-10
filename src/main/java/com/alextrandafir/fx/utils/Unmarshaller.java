package com.alextrandafir.fx.utils;

import com.alextrandafir.fx.model.Day;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

public class Unmarshaller {

    public Day unmarshal (String xml) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
//        InputSource is = new InputSource(new StringReader(xml));
        Document document = builder.parse(xml);
        return new Day();
    }
}
