package com.nrha.reinersuite.controller;

import com.nrha.reinersuite.models.dashboard.Dashboard;
import com.nrha.reinersuite.models.dashboard.DashboardType;
import com.nrha.reinersuite.models.users.SecurityRole;
import com.nrha.reinersuite.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @RolesAllowed(SecurityRole.ROLE_USER)
    @GetMapping("/components")
    public List<Dashboard> getUserDashboardComponents() {
        return this.dashboardService.getUserDashboardComponents();
    }

    @RolesAllowed(SecurityRole.ROLE_USER)
    @GetMapping("/components/list")
    public List<DashboardType> getUserDashboardTypeAvailable() {
        return this.dashboardService.getUserDashboardTypeAvailable();
    }
}
