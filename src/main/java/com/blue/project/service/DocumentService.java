package com.blue.project.service;

import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.users.DocumentSearch;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    StatusMessage uploadDocument(MultipartFile file);

    ResponseEntity<byte[]> downloadDocument(Long documentId);

    DocumentSearch search(DocumentSearch docSearch);
}
