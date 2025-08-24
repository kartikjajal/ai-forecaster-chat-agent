package com.ai.forecaster.service;

import com.ai.forecaster.chat.ForecasterAI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForecasterAIService {
    @Autowired
    ForecasterAI chatModel;

    public String chat(String input) {
        return chatModel.chat(input);
    }
}
