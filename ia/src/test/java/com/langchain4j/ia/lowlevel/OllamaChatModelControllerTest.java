package com.langchain4j.ia.lowlevel;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.langchain4j.ia.lowlevel.Ollama.OllamaChatModelController;
import com.langchain4j.ia.lowlevel.Ollama.OllamaConfig;

import dev.langchain4j.model.chat.ChatModel;

@WebMvcTest(OllamaChatModelController.class)
@Import(OllamaConfig.class)
class OllamaChatModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ChatModel chatModel;

    @Test
    void model_ShouldReturnDefaultResponse_WhenNoMessageProvided() throws Exception {
        // Arrange
        String expectedResponse = "Hello! How can I help you?";
        when(chatModel.chat("Hello")).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(get("/ollama/model"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(chatModel).chat("Hello");
    }

    @Test
    void model_ShouldReturnChatResponse_WhenMessageProvided() throws Exception {
        // Arrange
        String message = "What is Spring Boot?";
        String expectedResponse = "Spring Boot is a framework...";
        when(chatModel.chat(message)).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(get("/ollama/model")
                        .param("message", message))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));

        verify(chatModel).chat(message);
    }

    @Test
    void modelPost_ShouldReturnChatResponse_WhenValidRequest() throws Exception {
        // Arrange
        String requestMessage = "Explain unit testing";
        String responseMessage = "Unit testing is a software testing method...";
        when(chatModel.chat(requestMessage)).thenReturn(responseMessage);

        String requestJson = """
                {
                    "message": "%s"
                }
                """.formatted(requestMessage);

        // Act & Assert
        mockMvc.perform(post("/ollama/model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value(responseMessage));

        verify(chatModel).chat(requestMessage);
    }

    @Test
    void modelPost_ShouldHandleEmptyMessage() throws Exception {
        // Arrange
        String emptyMessage = "";
        String responseMessage = "I need more context";
        when(chatModel.chat(emptyMessage)).thenReturn(responseMessage);

        String requestJson = """
                {
                    "message": ""
                }
                """;

        // Act & Assert
        mockMvc.perform(post("/ollama/model")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value(responseMessage));

        verify(chatModel).chat(emptyMessage);
    }

}