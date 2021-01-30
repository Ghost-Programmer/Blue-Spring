package com.blue.project.controller;

import com.blue.project.dto.users.Registration;
import com.blue.project.dto.ScheduledMaintenance;
import com.blue.project.dto.StatusMessage;
import com.blue.project.dto.SystemInfo;
import com.blue.project.service.MaintenanceService;
import com.blue.project.service.SystemService;
import com.blue.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private MaintenanceService maintenanceService;

    @Autowired
    private SystemService systemService;

    @PostMapping("/user/register")
    @PreAuthorize("permitAll()")
    public StatusMessage registerUser(@RequestBody Registration registration) {
        return this.userService.registerUser(registration);
    }

    @GetMapping("/user/verification/{token}")
    @PreAuthorize("permitAll()")
    public StatusMessage userVerification(@PathVariable String token) {
        return this.userService.userVerification(token);
    }

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
