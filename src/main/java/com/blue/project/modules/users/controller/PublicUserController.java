package com.blue.project.modules.users.controller;

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
public class PublicUserController {
    @Autowired
    private UserService userService;

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
}
