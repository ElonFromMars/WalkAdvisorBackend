package com.petproject.walkadvisor.service.llm.dto;

import java.util.List;

public record ChatResponse(List<Choice> choices) {}

