package fr.efrei.springrag.web.rest;

import fr.efrei.springrag.domain.Document;
import fr.efrei.springrag.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class DocumentResource {

    private final DocumentService documentService;

    public DocumentResource(DocumentService documentService) {
        this.documentService = documentService;
    }


    @PostMapping("/documents")
    public ResponseEntity<Document> createDocument(@RequestBody Document document) throws URISyntaxException {
        Document result = documentService.buildAndSaveDocument(document);
        return ResponseEntity
                .created(new URI("/documents/" + result.getId()))
                .body(result);
    }

    @GetMapping("/documents")
    public ResponseEntity<List<Document>> findAllDocuments() throws URISyntaxException {
        List<Document> result = documentService.findAllDocuments();
        return ResponseEntity
                .ok()
                .body(result);
    }

    @GetMapping("/documentsById")
    public ResponseEntity<Optional<Document>> findDocumentById(Long id) throws URISyntaxException {
        Optional<Document> result = documentService.findDocumentById(id);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @DeleteMapping("/documents/{id}")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) throws URISyntaxException {
        documentService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }

//    @PostMapping("/documents")
//    public ResponseEntity<Document> addDocument(RequestBody Document){
//        return "Hello";
//    }
//
//    @PostMapping
//    public ResponseEntity<Document> getDocument(@PathVariable(value = "value", response = false)) final String{
//        return "Hello";
//    }
}