package com.ai.forecaster.config;

import com.ai.forecaster.chat.ForecasterAI;
import com.ai.forecaster.tool.ForecasterTool;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ForecasterAIConfig {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private ForecasterTool forecasterTool;

   @Bean
    public ForecasterAI aiService() {
        return AiServices.builder(ForecasterAI.class)
                .chatModel(chatModel)
                .tools(forecasterTool)
                .chatMemory(MessageWindowChatMemory.builder().maxMessages(5).build())
                .build();
    }
}
