package com.ai.forecaster.controller;

import com.ai.forecaster.service.ForecasterAIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ChatController {

    @Autowired
    private ForecasterAIService forecasterAIService;

    @GetMapping("chat")
    public String chat(@RequestParam("input") String input) {
        return forecasterAIService.chat(input);
    }
}
