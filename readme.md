# **DocQuery**
DocQuery is an AI-powered document querying system built with Spring Boot and Java 21.
It allows users to upload documents, extract text, generate embeddings, and perform semantic search using a Retrieval-Augmented Generation (RAG) workflow. The project uses PostgreSQL with PGVector for vector storage and Spring AI for LLM integrations.
---
## **Tech Stack**
* Java 21
* Spring Boot
* Spring AI (OpenAI + Vector Store)
* PostgreSQL + PGVector
* Apache Tika
* Maven
---
## **Current Functionality**
* Upload documents
* Parse and extract text
* Generate embeddings
* Store vectors in PostgreSQL
* Query indexed documents using natural language
---
## **How to Run**
```
mvn spring-boot:run
```
Swagger UI (when enabled):
`http://localhost:8080/swagger-ui/index.html`
---