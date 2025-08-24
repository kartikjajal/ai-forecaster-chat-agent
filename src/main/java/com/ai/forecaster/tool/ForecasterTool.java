package com.ai.forecaster.tool;

import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class ForecasterTool {

    public static final String FORECAST_URL = "https://api.open-meteo.com/v1/forecast?latitude=%s&longitude=%s&daily=sunrise,sunset&timezone=IST";

    @Tool("This tool is used to return latest date and time information.")
    public LocalDateTime getLocalDateTime(@P("""
            The Java ZoneId for the country/city for which date and time is requested.
            If no zoneId or timezone is provided consider IST as default."""
    ) String zoneId){
        log.info("getLocalDateTime for zoneId = {}", zoneId);
        return LocalDateTime.now(ZoneId.of(zoneId));
    }

    @Tool(value = """
            This tool is used to return sunrise & sunset information for the given latitude and longitude.
            It returns response that contains sunrise/sunset information on daily for next 7 days. It return response in
            HTML format.
            """)
    public String getSunriseAndSunsetTime(@P("latitude of the city/country") String latitude,
                                          @P("longitude of the city/country") String longitude) {
        RestClient restClient = RestClient.builder()
                .baseUrl(String.format(FORECAST_URL, latitude, longitude))
                .build();
        return restClient.get()
                .retrieve()
                .body(String.class);
    }

    @Tool(value = """
            This tool is used to return marine weather information like wave heigh, wave direction, wave period for the given latitude and longitude.
            It returns response that contains wave heigh, wave direction, wave period information on daily. It return response in
            HTML format.
            """)
    public String getMarineForecast(@P("latitude of the city/country") String latitude,
                                          @P("longitude of the city/country") String longitude,
                                    @P("forecast days, default is 7") int forecastDays) {
        RestClient restClient = RestClient.builder()
                .baseUrl(String.format("https://marine-api.open-meteo.com/v1/marine?latitude=%s&longitude=%s&hourly=wave_height,wave_direction,wave_period&forecast_days=%s", latitude, longitude, forecastDays))
                .build();
        return restClient.get()
                .retrieve()
                .body(String.class);
    }
}
