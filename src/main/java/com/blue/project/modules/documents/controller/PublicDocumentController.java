package com.blue.project.modules.documents.controller;

import com.blue.project.modules.documents.dto.MenuDto;
import com.blue.project.modules.documents.services.DocumentService;
import com.blue.project.modules.documents.services.MenuService;
import com.blue.project.modules.documents.services.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicDocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PageService PageService;

    @GetMapping("/document/{uuid}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable("uuid") String uuid) {
        return documentService.downloadDocumentByUuid(uuid);
    }

    @GetMapping("/menu/{name}")
    @PreAuthorize("permitAll()")
    public MenuDto getDisplayMenu(@PathVariable ("name") String menuName) {
        return menuService.getMenuPublic(menuName);
    }
}
