package com.vamu_rec_rag.demo.MultiAiService.controller.exception;

import java.time.Instant;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents the StandardError in the system")
public class StandardError {

    @Schema(description = "Timestamp of the error", example = "2024-04-27T12:34:56Z", required = true)
    private Instant timestamp;

    @Schema(description = "Status code of the error", example = "404", required = true)
    private Integer status;

    @Schema(description = "Error description", example = "Not Found", required = true)
    private String error;

    @Schema(description = "Detailed error message", example = "The requested resource was not found", required = true)
    private String message;
    
    @Schema(description = "Path where the error occurred", example = "/api/users/123", required = true)
    private String path;

}