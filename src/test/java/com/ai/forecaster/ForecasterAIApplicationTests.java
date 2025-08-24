package com.ai.forecaster;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "AZURE_OPENAI_KEY=test-api-key",
        "langchain4j.openai.model=gpt-3.5-turbo"
})
class ForecasterAIApplicationTests {

	@Test
	void contextLoads() {
	}

}
