package com.langchain4j.ia.lowlevel.DeepSeek;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.http.client.jdk.JdkHttpClientBuilderFactory;
import dev.langchain4j.model.openai.OpenAiChatModel;

@Configuration
public class DeepSeekConfig {

    @Value("${langchain4j.deepseek.chat-model.base-url}")
    private String baseUrl;

    @Value("${langchain4j.deepseek.chat-model.api-key}")
    private String deepseekApiKey;

    @Value("${langchain4j.deepseek.chat-model.model-name}")
    private String deepseekModelName;

    @Value("${langchain4j.deepseek.chat-model.temperature}")
    private Double deepseekTemperature;

    @Value("${langchain4j.deepseek.chat-model.timeout}")
    private Duration deepseekTimeout;

    @Value("${langchain4j.deepseek.chat-model.log-requests}")
    private boolean deepseekLogRequests;

    @Value("${langchain4j.deepseek.chat-model.log-responses}")
    private boolean deepseekLogResponses;

    @Bean("deepseekChatModel")
    public OpenAiChatModel deepseekChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(deepseekApiKey)
                .modelName(deepseekModelName)
                .temperature(deepseekTemperature)
                .timeout(deepseekTimeout)
                .logRequests(deepseekLogRequests)
                .logResponses(deepseekLogResponses)
                .baseUrl(baseUrl)
                .httpClientBuilder(new JdkHttpClientBuilderFactory().create())
                .build();
    }
}
