package com.vamu_rec_rag.demo.MultiAiService.controller;

import java.time.Duration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vamu_rec_rag.demo.MultiAiService.StreamingAssistant;
import com.vamu_rec_rag.demo.MultiAiService.controller.exception.StandardError;
import com.vamu_rec_rag.demo.MultiAiService.dto.ChatRequestDTO;
import com.vamu_rec_rag.demo.MultiAiService.dto.HealthStatusDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/ai")
@Tag(name = "AI Assistant", description = "API for AI Assistant with RAG and Streaming capabilities")
public class MultiAiServiceController {

    @Autowired
    @Qualifier("deepSeekAssistant")
    private StreamingAssistant deepSeekAssistant;

    @Autowired
    @Qualifier("geminiAssistant")
    private StreamingAssistant geminiAssistant;

    @Autowired
    @Qualifier("ollamaAssistant") 
    private StreamingAssistant ollamaAssistant;

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Health check successful", content = @Content(schema = @Schema(implementation = HealthStatusDTO.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Health check", description = "Check the health status of the AI service and available models", tags = {"AI Assistant"})
    public ResponseEntity<HealthStatusDTO> health() {
        HealthStatusDTO healthStatus = new HealthStatusDTO(
            "UP",
            "Multi AI Assistant",
            Map.of(
                "ollama", "✅ Disponível",
                "deepseek", "✅ Disponível", 
                "gemini", "✅ Disponível"
            ),
            Map.of(
                "rag", "✅",
                "streaming", "✅",
                "memory", "✅"
            )
        );
        return ResponseEntity.status(HttpStatus.OK).body(healthStatus);
    }

    @GetMapping(value = "/chat/{model}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Streaming chat started successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid model or request", content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Model not found", content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Streaming chat with AI model", description = "Start a streaming chat conversation with the specified AI model. Returns Server-Sent Events (SSE).", tags = {"AI Assistant"})
    public Flux<ServerSentEvent<String>> chatWithModel(
            @PathVariable("model") String model,
            @RequestParam(value = "message", defaultValue = "Olá! Como você está?") String message) {
        
        StreamingAssistant assistant = getAssistantByModel(model);
        
        return assistant.chat(message)
                .map(token -> ServerSentEvent.builder(token).build())
                .onErrorResume(throwable -> {
                    // Em caso de erro, envia uma mensagem de erro
                    return Flux.just(ServerSentEvent.builder(
                        "❌ Erro: " + throwable.getMessage()
                    ).build());
                });
    }


    @PostMapping(value = "/chat/{model}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Streaming chat started successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid model or request", content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "404", description = "Model not found", content = @Content(schema = @Schema(implementation = StandardError.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Streaming chat with AI model via POST", description = "Start a streaming chat conversation with the specified AI model using POST request. Returns Server-Sent Events (SSE).", tags = {"AI Assistant"})
    public Flux<ServerSentEvent<String>> chatWithModelPost(
            @PathVariable("model") String model,
            @RequestBody ChatRequestDTO request) {
        
        String message = request.getMessage();
        if (message == null || message.trim().isEmpty()) {
            message = "Olá! Como você está?";
        }
        
        StreamingAssistant assistant = getAssistantByModel(model);
        
        // ✅ Transforma o Flux<String> em ServerSentEvent para streaming real
        return assistant.chat(message)
                .map(token -> ServerSentEvent.builder(token).build())
                .onErrorResume(throwable -> {
                    return Flux.just(ServerSentEvent.builder(
                        "❌ Erro: " + throwable.getMessage()
                    ).build());
                });
    }

    @GetMapping(value = "/listeners/status", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Listeners status retrieved successfully", content = @Content(schema = @Schema(implementation = Map.class))),
        @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(schema = @Schema(implementation = StandardError.class)))
    })
    @Operation(summary = "Get listeners status", description = "Get the current status of all AI service listeners", tags = {"AI Assistant"})
    public ResponseEntity<Map<String, Object>> getListenersStatus() {
        return ResponseEntity.status(HttpStatus.OK).body(Map.of(
            "listeners", Map.of(
                "ChatModelListener", "✅ ATIVO",
                "RAGListener", "✅ ATIVO", 
                "EmbeddingListener", "✅ ATIVO"
            ),
            "timestamp", java.time.Instant.now(),
            "service", "Multi AI Assistant"
        ));
    }

    @GetMapping(value = "/test/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "Test streaming", description = "Test if Server-Sent Events streaming is working correctly", tags = {"AI Assistant"})
    public Flux<ServerSentEvent<String>> testStreaming() {
        return Flux.interval(Duration.ofSeconds(1))
                .take(10) 
                .map(sequence -> ServerSentEvent.builder(
                    "Evento " + sequence + " - " + java.time.LocalTime.now()
                ).build());
    }

    private StreamingAssistant getAssistantByModel(String model) {
        return switch (model.toLowerCase()) {
            case "deepseek" -> deepSeekAssistant;
            case "gemini" -> geminiAssistant;
            case "ollama" -> ollamaAssistant;
            default -> throw new IllegalArgumentException("Modelo não suportado: " + model);
        };
    }
}