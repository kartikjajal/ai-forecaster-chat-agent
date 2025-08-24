package com.ai.forecaster.service;

import com.ai.forecaster.tool.ForecasterTool;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        ForecasterTool.class
})
@Disabled
class ForecasterToolTest {

    @Autowired
    private ForecasterTool provider;

    @MockitoBean
    private RestClient restClient;

    @Test
    void testGetLocalDateTime_withValidZoneId() {
        String zoneId = "Asia/Kolkata";
        LocalDateTime result = provider.getLocalDateTime(zoneId);
        assertNotNull(result);
        assertEquals(ZoneId.of(zoneId), result.atZone(ZoneId.of(zoneId)).getZone());
    }

    @Test
    void testGetLocalDateTime_withDefaultZoneId() {
        String zoneId = "Asia/Kolkata";
        LocalDateTime result = provider.getLocalDateTime(zoneId);
        assertNotNull(result);
    }

    @Test
    void testGetSunriseAndSunsetTime() {
        String latitude = "19.0760";
        String longitude = "72.8777";
        String response = provider.getSunriseAndSunsetTime(latitude, longitude);
        assertNotNull(response);
    }

    @Test
    void testGetMarineForecast() {
        String latitude = "19.0760";
        String longitude = "72.8777";
        int forecastDays = 7;

        String response = provider.getMarineForecast(latitude, longitude, forecastDays);
        assertNotNull(response);
    }
}