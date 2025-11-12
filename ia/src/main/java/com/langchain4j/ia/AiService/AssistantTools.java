package com.langchain4j.ia.AiService;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

import dev.langchain4j.agent.tool.Tool;
import io.micrometer.observation.annotation.Observed;

@Service
public class AssistantTools {

    @Tool
    @Observed
    public String currentTime() {
        return LocalTime.now().toString();
    }

}
