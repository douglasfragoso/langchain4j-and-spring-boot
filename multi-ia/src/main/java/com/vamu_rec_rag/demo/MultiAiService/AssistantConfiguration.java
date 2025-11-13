package com.vamu_rec_rag.demo.MultiAiService;

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

    // CORRIGIR: Criar um ContentRetriever dummy para teste
    @Bean
    ContentRetriever contentRetriever() {
        // Retriever vazio para testes - você pode substituir por um real depois
        return query -> java.util.Collections.emptyList();
    }

    @Bean
    public StreamingAssistant deepSeekAssistant(
            @Qualifier("deepseekStreamingChatModel") StreamingChatModel deepseekStreamingChatModel, // CORRIGIDO
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
            @Qualifier("geminiStreamingChatModel") StreamingChatModel geminiStreamingChatModel, // CORRIGIDO
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
    public StreamingAssistant ollamaAssistant( // MUDAR NOME do método
            @Qualifier("ollamaChatModel") StreamingChatModel ollamaStreamingChatModel, // CORRIGIDO
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