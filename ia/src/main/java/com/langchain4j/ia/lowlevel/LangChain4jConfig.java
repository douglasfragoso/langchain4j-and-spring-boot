package com.langchain4j.ia.lowlevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.ollama.OllamaChatModel;

import java.time.Duration;

@Configuration
public class LangChain4jConfig {

    @Value("${langchain4j.ollama.chat-model.base-url}")
    private String baseUrl;

    @Value("${langchain4j.ollama.chat-model.model-name}")
    private String modelName;

    @Value("${langchain4j.ollama.chat-model.temperature}")
    private Double temperature;

    @Value("${langchain4j.ollama.chat-model.timeout}")
    private Duration timeout;

    @Bean
    public OllamaChatModel ollamaChatModel() {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(temperature)
                .timeout(timeout)
                .build();
    }
}