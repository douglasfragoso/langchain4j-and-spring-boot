package com.langchain4j.ia.MultiAiService;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ai", produces = "application/json")
public class MultiAiServiceController {
    
    
    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
            "status", "UP",
            "service", "MVP AI Assistant",
            "models", Map.of(
                "ollama", "✅ Disponível",
                "deepseek", "✅ Disponível", 
                "gemini", "✅ Disponível"
            ),
            "features", Map.of(
                // "rag", "✅",
                "streaming", "✅",
                "memory", "✅"
                // "tools", "✅"
            )
        );
    }

}
