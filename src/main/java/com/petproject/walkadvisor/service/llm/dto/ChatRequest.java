package com.petproject.walkadvisor.service.llm.dto;

import java.util.List;

public record ChatRequest(
        List<ChatMessage> messages,
        double temperature,
        int max_tokens,
        String mode // Specific to text-generation-webui, 'chat' is common
) {}