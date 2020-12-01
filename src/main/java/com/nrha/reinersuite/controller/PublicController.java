package com.nrha.reinersuite.controller;

import com.nrha.reinersuite.dto.Registration;
import com.nrha.reinersuite.dto.ScheduledMaintenance;
import com.nrha.reinersuite.dto.StatusMessage;
import com.nrha.reinersuite.service.MaintenanceService;
import com.nrha.reinersuite.service.UserService;
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

    @PostMapping("/user/register")
    @PreAuthorize("permitAll()")
    public StatusMessage registerUser(@RequestBody Registration registration) throws Exception {
        return this.userService.registerUser(registration);
    }

    @GetMapping("/user/verification/{token}")
    @PreAuthorize("permitAll()")
    public StatusMessage userVerification(@PathVariable String token) throws Exception {
        return this.userService.userVerification(token);
    }

    @GetMapping("/maintrance/scheduled")
    @PreAuthorize("permitAll()")
    public ScheduledMaintenance getScheduleMaintenance() {
        return maintenanceService.getNextScheduledMaintenance();
    }
}
