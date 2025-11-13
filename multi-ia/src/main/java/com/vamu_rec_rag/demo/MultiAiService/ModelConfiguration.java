package com.vamu_rec_rag.demo.MultiAiService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.http.client.jdk.JdkHttpClientBuilderFactory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiStreamingChatModel;
import dev.langchain4j.model.ollama.OllamaStreamingChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

@Configuration
public class ModelConfiguration {

    // Use valores default para evitar erro se propriedade não existir
    @Value("${langchain4j.open-ai.deepseek.streaming-chat-model.api-key}")
    private String deepseekApiKey;

    @Value("${langchain4j.open-ai.deepseek.streaming-chat-model.model-name}")
    private String deepseekModelName;

    @Value("${langchain4j.google-ai-gemini.streaming-chat-model.api-key}")
    private String geminiApiKey;

    @Value("${langchain4j.google-ai-gemini.streaming-chat-model.model-name}")
    private String geminiModelName;

    @Value("${langchain4j.ollama.streaming-chat-model.base-url}")
    private String ollamaBaseUrl;

    @Value("${langchain4j.ollama.streaming-chat-model.model-name}")
    private String ollamaModelName;

    @Bean
    public StreamingChatModel deepseekStreamingChatModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(deepseekApiKey)
                .baseUrl("https://api.deepseek.com")
                .modelName(deepseekModelName)
                .temperature(0.7)
                .logRequests(true)
                .logResponses(true)
                .httpClientBuilder(new JdkHttpClientBuilderFactory().create())
                .build();
    }

    @Bean
    public StreamingChatModel geminiStreamingChatModel() {
        return GoogleAiGeminiStreamingChatModel.builder()
                .apiKey(geminiApiKey)
                .modelName(geminiModelName)
                .temperature(0.7)
                .logRequests(true)
                .logResponses(true)
                .httpClientBuilder(new JdkHttpClientBuilderFactory().create())
                .build();
    }

    @Bean
    public StreamingChatModel ollamaChatModel() {
        return OllamaStreamingChatModel.builder()
                .baseUrl(ollamaBaseUrl)
                .modelName(ollamaModelName)
                .temperature(0.7)
                .logRequests(true)
                .logResponses(true)
                .httpClientBuilder(new JdkHttpClientBuilderFactory().create())
                .build();
    }
}