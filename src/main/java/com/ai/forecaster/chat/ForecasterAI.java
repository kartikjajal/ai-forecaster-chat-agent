package com.ai.forecaster.chat;

import dev.langchain4j.service.SystemMessage;

public interface ForecasterAI {
    @SystemMessage("""
            You are an AI assistant that provides information about marine weather,sunrise and sunset times.
            You are an marine weather/sunset/sunrise forecaster, you forecast for the given city.
            It return response in HTML format.
            """)
    String chat(String input);
}
