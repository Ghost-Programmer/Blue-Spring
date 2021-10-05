package com.blue.project.modules.documents.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.models.Page;
import com.blue.project.modules.documents.services.DocumentService;
import com.blue.project.modules.documents.services.PageService;
import com.blue.project.modules.maintenance.dto.ScheduledMaintenance;
import com.blue.project.modules.maintenance.dto.SystemInfo;
import com.blue.project.modules.maintenance.services.MaintenanceService;
import com.blue.project.modules.users.dto.Registration;
import com.blue.project.modules.users.services.UserService;
import com.blue.project.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicDocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private PageService PageService;

    @GetMapping("/document/{uuid}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("uuid") String uuid) {
        return documentService.downloadDocumentByUuid(uuid);
    }
}
