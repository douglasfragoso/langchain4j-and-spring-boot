package com.langchain4j.ia.lowlevel.Gemini;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.http.client.jdk.JdkHttpClientBuilderFactory;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;

@Configuration
public class GeminiConfig {

    @Value("${langchain4j.gemini.chat-model.api-key}")
    private String geminiApiKey;

    @Value("${langchain4j.gemini.chat-model.model-name}")
    private String geminiModelName;

    @Value("${langchain4j.gemini.chat-model.temperature}")
    private Double geminiTemperature;

    @Value("${langchain4j.gemini.chat-model.timeout}")
    private Duration geminiTimeout;

    @Value("${langchain4j.gemini.chat-model.log-requests}")
    private boolean geminiLogRequests;

    @Value("${langchain4j.gemini.chat-model.log-responses}")
    private boolean geminiLogResponses;

    @Bean("geminiChatModel")
    public GoogleAiGeminiChatModel geminiChatModel() {
        return GoogleAiGeminiChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName(geminiModelName)
                .temperature(geminiTemperature)
                .timeout(geminiTimeout)
                .logRequests(geminiLogRequests)
                .logResponses(geminiLogResponses)
                .httpClientBuilder(new JdkHttpClientBuilderFactory().create())
                .build();
    }
}