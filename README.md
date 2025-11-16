# 🤖 Multi AI Assistant with RAG

## 📋 Sobre o Projeto

Sistema de assistente de IA com múltiplos provedores (DeepSeek, Gemini, Ollama) integrando **RAG (Retrieval-Augmented Generation)** e **streaming em tempo real**. A aplicação permite conversas contextuais com base em documentos carregados, utilizando técnicas avançadas de processamento de linguagem natural.

## 🚀 Características Principais

### 🤖 Multi-Modelo
- **DeepSeek**: API compatível com OpenAI
- **Google Gemini**: Modelo avançado do Google  
- **Ollama**: Modelos locais (Llama 3.1 8B)

### 🔍 Sistema RAG Avançado
- **Document Splitting**: Chunks otimizados (500 caracteres + 50 overlap)
- **Embedding Local**: Modelo BGE Small EN V1.5 Quantized (ONNX)
- **Busca Semântica**: Recuperação por similaridade vetorial
- **In-Memory Store**: Armazenamento eficiente de embeddings

### ⚡ Tecnologias
- **Java 25** + **Spring Boot 3.5.7**
- **LangChain4j** - Framework de IA
- **WebFlux** - Streaming reativo
- **Swagger/OpenAPI** - Documentação interativa
- **Actuator** - Monitoramento e métricas

## 📖 Documentação Interativa

Acesse a documentação Swagger em: http://localhost:8080/swagger-ui.html

## 🧪 Testes

### Endpoint - Ollama
```sh
 curl -X POST http://localhost:8080/ai/chat/ollama -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"
```

### Endpoint - Gemini
```sh
curl -X POST http://localhost:8080/ai/chat/gemini -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"
```    
### Endpoint - DeepSeek
```sh
curl -X POST http://localhost:8080/ai/chat/deepseek -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"
``` 
### Endpoint - Verificar o helth do sistema
```sh
curl "http://localhost:8080/ai/health"
``` 
### Endpoint - Verificar status dos listeners (RAG, Chat, Embedding)
```sh
curl "http://localhost:8080/ai/listeners/status"
``` 
### Endpoint - Teste de streaming básico
```sh
curl -N "http://localhost:8080/ai/test/stream"
``` 

### IA Teste:

     curl -X POST http://localhost:8080/ollama/model -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

     curl -X POST http://localhost:8080/deepseek/model -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

     curl -X POST http://localhost:8080/gemini/model -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"  

### EASY-RAG Teste:

     curl -X POST http://localhost:8080/ai/chat/ollama -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"

     curl -X POST http://localhost:8080/ai/chat/gemini -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"

     curl -X POST http://localhost:8080/ai/chat/deepseek -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"

Feito com carinho por Douglas Fragoso 👊