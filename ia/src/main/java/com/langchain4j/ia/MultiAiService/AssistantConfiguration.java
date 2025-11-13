package com.langchain4j.ia.MultiAiService;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.*;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;

@Configuration
public class AssistantConfiguration {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    ChatModelListener chatModelListener() {
        return new MyChatModelListener();
    }

    @Bean
    public StreamingAssistant deepSeekAssistant(
            @Qualifier("deepseekStreamingChatModel") StreamingChatModel deepseekStreamingChatModel,
            ContentRetriever contentRetriever,
            ChatMemory chatMemory
    ) {
        return AiServices.builder(StreamingAssistant.class)
                .streamingChatModel(deepseekStreamingChatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }

    @Bean
    public StreamingAssistant geminiAssistant(
            @Qualifier("geminiStreamingChatModel") StreamingChatModel geminiStreamingChatModel,
            ContentRetriever contentRetriever,
            ChatMemory chatMemory
    ) {
        return AiServices.builder(StreamingAssistant.class)
                .streamingChatModel(geminiStreamingChatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }

    @Bean
    public StreamingAssistant streamingRagAssistant(
            @Qualifier("ollamaCChatModel") StreamingChatModel ollamaStreamingChatModel,
            ContentRetriever contentRetriever,
            ChatMemory chatMemory
    ) {
        return AiServices.builder(StreamingAssistant.class)
                .streamingChatModel(ollamaStreamingChatModel)
                .contentRetriever(contentRetriever)
                .chatMemory(chatMemory)
                .build();
    }
}