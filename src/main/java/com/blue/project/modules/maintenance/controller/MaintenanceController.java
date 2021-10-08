package com.blue.project.modules.maintenance.controller;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.maintenance.dto.MaintenanceSearch;
import com.blue.project.modules.maintenance.models.Scheduled;
import com.blue.project.modules.maintenance.services.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/maintenance", produces = MediaType.APPLICATION_JSON_VALUE)
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("search")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_ADMIN_MAINTENANCE) ")
    public MaintenanceSearch search(@RequestBody MaintenanceSearch search) {
        return this.maintenanceService.search(search);
    }

    @PostMapping("")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_ADMIN_MAINTENANCE) ")
    public Scheduled save(@RequestBody Scheduled scheduled) {
        return this.maintenanceService.saveScheduledMaintenance(scheduled);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole(T(com.blue.project.modules.users.models.SecurityRole).ROLE_ADMIN_MAINTENANCE) ")
    public StatusMessage delete (@PathVariable("id") Long id) {
        return this.maintenanceService.deleteScheduledMaintenance(id);
    }

    @GetMapping("/logging/{level}")
    @RolesAllowed("ROLE_DEVELOPER")
    public StatusMessage setLoggingLevel(@PathVariable("level") String level) {
        return this.maintenanceService.setLoggingLevel(level);
    }
}
