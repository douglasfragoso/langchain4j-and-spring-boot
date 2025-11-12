package com.langchain4j.ia.AiService;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {
    
    @SystemMessage(fromResource = "prompt.md")
    String chat(String userMessage);
}
