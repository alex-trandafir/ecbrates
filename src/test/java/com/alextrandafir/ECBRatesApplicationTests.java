package com.alextrandafir;

import com.alextrandafir.exceptions.NoRatesException;
import com.alextrandafir.fx.service.RatesDay;
import com.alextrandafir.fx.service.RatesService;
import com.alextrandafir.fx.utils.ResourceReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.Resource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static com.alextrandafir.fx.client.RatesClient.EURO_FX_DAILY;
import static com.alextrandafir.fx.client.RatesClient.EURO_FX_HISTORY;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Slf4j
class ECBRatesApplicationTests {

    @Autowired
    @Spy
    RatesService ratesService;

    @Mock
    private RestTemplate restTemplate;

    @Value("classpath:eurofxref-daily.xml")
    private Resource eurofxrefDailyResource;

    @Value("classpath:eurofxref-hist-90d.xml")
    private Resource eurofxrefHistResource;

    @Value("${quartz.interval}")
    Integer jobIntervalSeconds;

    @Autowired
    CacheManager cacheManager;

    @Test
    public void testEHCacheRetrieval() throws NoRatesException, InterruptedException {

        when(restTemplate.getForObject(EURO_FX_DAILY, String.class))
            .thenReturn(ResourceReader.asString(eurofxrefDailyResource));

        when(restTemplate.getForObject(EURO_FX_HISTORY, String.class))
                .thenReturn(ResourceReader.asString(eurofxrefHistResource));

        ratesService.getRatesClient().setRestTemplate(restTemplate);

        ratesService.getRates(LocalDate.parse("2020-11-01"), LocalDate.parse("2020-12-14"));

        assertTrue(cacheManager.getCache("ratesCache").get(RatesDay.TODAY) != null);
        assertTrue(cacheManager.getCache("ratesCache").get(RatesDay.OLDER) != null);
    }

}
