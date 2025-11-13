package com.langchain4j.ia.MultiAiService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;

public class MyChatModelListener implements ChatModelListener{

    private static final Logger log = LoggerFactory.getLogger(MyChatModelListener.class);

    @Override
    public void onRequest(ChatModelRequestContext requestContext){
        log.info("onRequest(): {}", requestContext.chatRequest());
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext){
        log.info("onResponse(): {}", responseContext.chatResponse());
    }
    
    @Override
    public void onError(ChatModelErrorContext errorContext){
        log.info("onError(): {}", errorContext.error().getMessage());
    }
    
}
