package com.blue.project.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/document", produces = MediaType.APPLICATION_JSON_VALUE)
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @RolesAllowed("ROLE_USER")
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public StatusMessage uploadDocument(@RequestParam("file") MultipartFile file) {
        return documentService.uploadDocument(file);
    }
}
