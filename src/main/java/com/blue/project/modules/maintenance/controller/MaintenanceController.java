package com.blue.project.modules.maintenance.controller;

import com.blue.project.modules.maintenance.dto.MaintenanceSearch;
import com.blue.project.modules.maintenance.services.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/maintenance", produces = MediaType.APPLICATION_JSON_VALUE)
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("search")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_DOCUMENT_MANAGER) ")
    public MaintenanceSearch search(@RequestBody MaintenanceSearch search) {
        return this.maintenanceService.search(search);
    }
}
