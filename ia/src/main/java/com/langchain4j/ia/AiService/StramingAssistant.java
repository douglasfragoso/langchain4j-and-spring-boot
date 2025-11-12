package com.langchain4j.ia.AiService;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;
import reactor.core.publisher.Flux;

@AiService
public interface StramingAssistant {

    @SystemMessage(fromResource = "prompt.md")
    Flux<String> chat(String userMessage);
}
