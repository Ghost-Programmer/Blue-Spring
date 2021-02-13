package com.blue.project.controller;


import com.blue.project.dto.system.SystemStatus;
import com.blue.project.service.SystemService;
import name.mymiller.nadia.system.SystemStatusReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/system", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemController {
    @Autowired
    private SystemService systemService;

    @RolesAllowed("ROLE_MONITOR")
    @GetMapping("/status")
    public List<SystemStatus> getSystemStatus() {
        return this.systemService.getSystemStatus();
    }

    @RolesAllowed("ROLE_MONITOR")
    @GetMapping("/status/current")
    public SystemStatusReport getCurrentSystemStatus() {
        return this.systemService.getCurrentSystemStatus();
    }

}
