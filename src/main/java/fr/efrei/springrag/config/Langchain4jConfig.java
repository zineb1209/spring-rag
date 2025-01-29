package fr.efrei.springrag.config;

import dev.langchain4j.data.segment.TextSegment;c
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import fr.efrei.springrag.rag.DocumentQueryRouter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Langchain4jConfig {

    @Bean
    ChatMemoryProvider chatMemoryProvider() {
        return memoryId -> MessageWindowChatMemory.withMaxMessages(10);
    }

    @Bean
    InMemoryEmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    EmbeddingModel embeddingModel() {
        return new AllMiniLmL6V2EmbeddingModel();
    }

    @Bean
    EmbeddingStoreContentRetriever contentRetriever(InMemoryEmbeddingStore<TextSegment> embeddingStore,
                                                    EmbeddingModel embeddingModel) {
        return new EmbeddingStoreContentRetriever(embeddingStore, embeddingModel);
    }

    @Bean
    RetrievalAugmentor retrievalAugmentor(ChatLanguageModel chatLanguageModel, ContentRetriever docuementContentRetriever) {
        return DefaultRetrievalAugmentor.builder()
                .queryRouter(new DocumentQueryRouter(chatLanguageModel, docuementContentRetriever))
                .build();
    }
}