package com.blue.project.modules.maintenance.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.documents.services.DocumentService;
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

@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicMaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private SystemService systemService;

    @GetMapping("/maintenance/scheduled")
    @PreAuthorize("permitAll()")
    public ScheduledMaintenance getScheduleMaintenance() {
        return maintenanceService.getNextScheduledMaintenance();
    }

    @GetMapping("/system/info")
    @PreAuthorize("permitAll()")
    public SystemInfo getSystemInfo() {
        return systemService.getSystemInfo();
    }

}