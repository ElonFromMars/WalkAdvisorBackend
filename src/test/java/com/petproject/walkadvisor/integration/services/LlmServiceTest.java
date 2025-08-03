package com.petproject.walkadvisor.integration.services;

import com.petproject.walkadvisor.service.llm.LlmService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest(LlmServiceTest.class)
public class LlmServiceTest {
    private static final Logger log = LoggerFactory.getLogger(LlmServiceTest.class);

    @Test
    void testLlmResponseExists() {
        var llmService = new LlmService("http://127.0.0.1:5000/v1/chat/completions");

        String result = llmService.queryLLM("What is the capital of Poland?");
        log.info("LLM Response: {}", result);
        assertNotNull(result, "LLM response should not be null");
    }
}
