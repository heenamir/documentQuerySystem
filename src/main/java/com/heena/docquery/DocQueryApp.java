package com.heena.docquery;

import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.autoconfigure.vectorstore.pgvector.PgVectorStoreAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/*
    excluding these classes till we setup AI API and databases
 */
@SpringBootApplication(exclude = { OpenAiAutoConfiguration.class, DataSourceAutoConfiguration.class, PgVectorStoreAutoConfiguration.class })
public class DocQueryApp {
    public static void main(String[] args) {
        SpringApplication.run(DocQueryApp.class, args);
    }
}