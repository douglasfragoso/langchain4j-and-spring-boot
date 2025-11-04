package com.langchain4j.ia.lowlevel.Ollama;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.http.client.jdk.JdkHttpClientBuilderFactory;
import dev.langchain4j.model.ollama.OllamaChatModel;

@Configuration
public class OllamaConfig {

    @Value("${langchain4j.ollama.chat-model.base-url}")
    private String baseUrl;

    @Value("${langchain4j.ollama.chat-model.model-name}")
    private String modelName;

    @Value("${langchain4j.ollama.chat-model.temperature}")
    private Double temperature;

    @Value("${langchain4j.ollama.chat-model.timeout}")
    private Duration timeout;

    @Bean("ollamaChatModel")
    public OllamaChatModel ollamaChatModel() {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .temperature(temperature)
                .timeout(timeout)
                .httpClientBuilder(new JdkHttpClientBuilderFactory().create())
                .build();
    }
}