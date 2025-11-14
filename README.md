# langchain4j-and-spring-boot
Langchain4j and Spring Boot example

IA Teste:

     curl -X POST http://localhost:8080/ollama/model -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

     curl -X POST http://localhost:8080/deepseek/model -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

     curl -X POST http://localhost:8080/gemini/model -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"  

Multi-IA Teste:

     curl -X POST http://localhost:8080/ai/chat/ollama -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

     curl -X POST http://localhost:8080/ai/chat/gemini -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

     curl -X POST http://localhost:8080/ai/chat/deepseek -H "Content-Type: application/json" -d "{\"message\": \"Quais são os principais pontos turísticos do Recife Antigo?\"}"

EASY-RAG Teste:

     curl -X POST http://localhost:8080/ai/chat/ollama -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"

     curl -X POST http://localhost:8080/ai/chat/gemini -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"

     curl -X POST http://localhost:8080/ai/chat/deepseek -H "Content-Type: application/json" -d "{\"message\": \"O que voce sabe sobre o sistema de recomendação?\"}"