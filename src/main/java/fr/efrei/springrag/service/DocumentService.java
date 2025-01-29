package fr.efrei.springrag.service;

import fr.efrei.springrag.domain.Document;
import fr.efrei.springrag.repository.DocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final Logger log;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
        this.log = LoggerFactory.getLogger(DocumentService.class);
    }

    public Document buildAndSaveDocument(Document document) {
        //log.debug("Request to buildAndSave Socuement : {}", document);
        return documentRepository.save(document);
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public Optional<Document> findDocumentById(Long id) {
        return documentRepository.findById(id);
    }

    public List<Document> findAllDocuments() {
        return documentRepository.findAll();
    }
}