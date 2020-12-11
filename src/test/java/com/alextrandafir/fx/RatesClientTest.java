//package com.alextrandafir.fx;
//
//import com.alextrandafir.fx.config.RatesClientConfig;
//import com.alextrandafir.fx.service.RatesService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.support.AnnotationConfigContextLoader;
//
//import javax.xml.bind.JAXBException;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {RatesClientConfig.class, RatesService.class}, loader = AnnotationConfigContextLoader.class)
//public class RatesClientTest {
//
//    @Autowired
//    RatesService ratesService;
//
//    @Test
//    public void testWebService() throws JAXBException {
//        String response = ratesService.getDailyRate();
//        System.out.println(response);
//    }
//}
