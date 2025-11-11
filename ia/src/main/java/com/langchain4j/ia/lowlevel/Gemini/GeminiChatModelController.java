package com.langchain4j.ia.lowlevel.Gemini;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.langchain4j.model.chat.ChatModel;

@RestController
@RequestMapping(value = "/gemini", produces = "application/json")
public class GeminiChatModelController {
    
    @Autowired
    @Qualifier("geminiChatModel")
    private ChatModel chatModel;

    @GetMapping("/model")
    public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return chatModel.chat(message);
    }

    @PostMapping("/model")
    public ChatResponse modelPost(@RequestBody ChatRequest request) {
        String response = chatModel.chat(request.message());
        return new ChatResponse(response);
    }

    // Records para o request/response
    public record ChatRequest(String message) {}
    public record ChatResponse(String response) {}
}