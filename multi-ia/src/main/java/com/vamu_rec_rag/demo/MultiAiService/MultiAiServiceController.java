package com.vamu_rec_rag.demo.MultiAiService;

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/ai")
public class MultiAiServiceController {

    @Autowired
    @Qualifier("deepSeekAssistant")
    private StreamingAssistant deepSeekAssistant;

    @Autowired
    @Qualifier("geminiAssistant")
    private StreamingAssistant geminiAssistant;

    @Autowired
    @Qualifier("ollamaAssistant") 
    private StreamingAssistant ollamaAssistant;

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> health() {
        return Map.of(
            "status", "UP",
            "service", "Multi AI Assistant",
            "models", Map.of(
                "ollama", "✅ Disponível",
                "deepseek", "✅ Disponível", 
                "gemini", "✅ Disponível"
            ),
            "features", Map.of(
                "rag", "✅",
                "streaming", "✅",
                "memory", "✅"
            )
        );
    }

    @GetMapping(value = "/chat/deepseek", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithDeepSeek(
            @RequestParam(value = "message", defaultValue = "Olá! Como você está?") String message) {
        return deepSeekAssistant.chat(message);
    }

    @GetMapping(value = "/chat/gemini", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithGemini(
            @RequestParam(value = "message", defaultValue = "Olá! Como você está?") String message) {
        return geminiAssistant.chat(message);
    }

    @GetMapping(value = "/chat/ollama", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithOllama(
            @RequestParam(value = "message", defaultValue = "Olá! Como você está?") String message) {
        return ollamaAssistant.chat(message);
    }

      // NOVOS Endpoints POST
    @PostMapping(value = "/chat/deepseek", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithDeepSeekPost(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            message = "Olá! Como você está?";
        }
        return deepSeekAssistant.chat(message);
    }

    @PostMapping(value = "/chat/gemini", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithGeminiPost(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            message = "Olá! Como você está?";
        }
        return geminiAssistant.chat(message);
    }

    @PostMapping(value = "/chat/ollama", produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<String> chatWithOllamaPost(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        if (message == null || message.trim().isEmpty()) {
            message = "Olá! Como você está?";
        }
        return ollamaAssistant.chat(message);
    }
}