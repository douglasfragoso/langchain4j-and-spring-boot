package com.langchain4j.ia.MultiAiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiStreamingChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

@Configuration
public class ModelConfiguration {

    @Value("${deepseek.api.key}")
    private String deepseekApiKey;

    @Value("${deepseek.model.name}")
    private String deepseekModelName;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    @Value("${gemini.model.name}")
    private String geminiModelName;

    @Value("${ollama.base.url}")
    private String ollamaBaseUrl;

    @Value("${ollama.model.name}")
    private String ollamaModelName;

    @Bean
    public StreamingChatModel deepseekStreamingChatModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(deepseekApiKey)
                .baseUrl("https://api.deepseek.com")
                .modelName(deepseekModelName)
                .temperature(0.7)
                .build();
    }

    @Bean
    public StreamingChatModel geminiStreamingChatModel() {
        return GoogleAiGeminiStreamingChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName(geminiModelName)
                .temperature(0.7)
                .build();
    }

    @Bean
    public StreamingChatModel ollamaCChatModel() {
        return OllamaStreamingChatModel.builder()
                .baseUrl(ollamaBaseUrl)
                .modelName(ollamaModelName)
                .temperature(0.7)
                .build();
    }
}