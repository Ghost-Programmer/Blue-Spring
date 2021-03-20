package com.blue.project.modules.documents.services;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.dto.DocumentSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    StatusMessage uploadDocument(MultipartFile file);

    ResponseEntity<byte[]> downloadDocument(Long documentId);

    DocumentSearch search(DocumentSearch docSearch);

    ResponseEntity<byte[]> downloadDocumentByUuid(String uuid);

    StatusMessage deleteDocument(Long id);
}
