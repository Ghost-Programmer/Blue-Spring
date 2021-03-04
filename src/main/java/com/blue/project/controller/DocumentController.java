package com.blue.project.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.documents.DocumentSearch;
import com.blue.project.models.users.User;
import com.blue.project.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @RequestMapping(value = "/download/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE })
    @PreAuthorize("hasRole(T(com.blue.project.models.users.SecurityRole).ROLE_USER) or hasRole(T(com.blue.project.models.users.SecurityRole).ROLE_APPROVER) or hasRole(T(com.blue.project.models.users.SecurityRole).ROLE_DOCUMENT_MANAGER) ")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("id") Long documentId) {
        return documentService.downloadDocument(documentId);
    }

    @PostMapping("doc-search")
    public DocumentSearch docSearch(@RequestBody DocumentSearch docSearch) {
        return documentService.search(docSearch);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole(T(com.blue.project.models.users.SecurityRole).ROLE_USER) or hasRole(T(com.blue.project.models.users.SecurityRole).ROLE_DOCUMENT_MANAGER) ")
    public StatusMessage deleteDocument(@PathVariable("id") Long id) {
        return documentService.deleteDocument(id);
    }
}
