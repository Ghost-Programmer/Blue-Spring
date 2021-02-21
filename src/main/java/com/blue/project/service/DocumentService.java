package com.blue.project.service;

import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.users.DocumentSearch;
import com.blue.project.models.users.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {

    StatusMessage uploadDocument(MultipartFile file);

    ResponseEntity<byte[]> downloadDocument(Long documentId);

    DocumentSearch search(DocumentSearch docSearch);
}
