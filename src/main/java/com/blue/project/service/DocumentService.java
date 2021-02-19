package com.blue.project.service;

import com.blue.project.dto.StatusMessage;
import org.springframework.web.multipart.MultipartFile;

public interface DocumentService {

    StatusMessage uploadDocument(MultipartFile file);
}
