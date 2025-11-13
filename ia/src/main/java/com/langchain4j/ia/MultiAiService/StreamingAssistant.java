package com.langchain4j.ia.MultiAiService;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

@AiService
public interface StreamingAssistant {

    @SystemMessage(fromResource = "prompt.md")
    Flux<String> chat(String userMessage);
}

