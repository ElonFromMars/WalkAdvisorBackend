package com.petproject.walkadvisor.service.llm;

import com.petproject.walkadvisor.service.llm.dto.ChatMessage;
import com.petproject.walkadvisor.service.llm.dto.ChatRequest;
import com.petproject.walkadvisor.service.llm.dto.ChatResponse;
import com.petproject.walkadvisor.service.llm.dto.Choice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class LlmService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiUrl;

    public LlmService(@Value("${llm.api.url}")String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String queryLLM(String prompt) {
        List<ChatMessage> messages = List.of(new ChatMessage("user", prompt));
        ChatRequest chatRequest = new ChatRequest(messages, 0.7, 256, "chat");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(chatRequest, headers);

        ChatResponse response = restTemplate.postForObject(apiUrl, entity, ChatResponse.class);

        if (response != null && response.choices() != null && !response.choices().isEmpty()) {
            Choice choice = response.choices().get(0);
            return choice.message().content();
        }

        return "Sorry, I could not get a response.";
    }
}