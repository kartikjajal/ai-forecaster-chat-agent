package com.ai.forecaster.controller;

import com.ai.forecaster.service.ForecasterAIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "AZURE_OPENAI_KEY=test-api-key",
        "langchain4j.openai.model=gpt-3.5-turbo"
})
public class ChatConrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ForecasterAIService forecasterAIService;

    @Test
    void chatEndpoint_returnsExpectedResponse() throws Exception {
        String input = "Hello";
        String expectedChatResponse = "Hi there!";
        when(forecasterAIService.chat(input)).thenReturn(expectedChatResponse);

        mockMvc.perform(get("/chat").param("input", input))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedChatResponse));
    }
}