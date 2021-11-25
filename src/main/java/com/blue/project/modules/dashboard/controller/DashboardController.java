package com.blue.project.modules.dashboard.controller;

import com.blue.project.modules.dashboard.models.Dashboard;
import com.blue.project.modules.dashboard.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(value = "/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @RolesAllowed("ROLE_USER")
    @GetMapping("/components")
    public List<Dashboard> getUserDashboardComponents() {
        return this.dashboardService.getUserDashboardComponents();
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/components/list")
    @Cacheable("dashboard_types")
    public List<Dashboard> getUserDashboardTypeAvailable() {
        return this.dashboardService.getUserAvailableDashboardComponents();
    }

    @RolesAllowed("ROLE_USER")
    @PostMapping("/components")
    public List<Dashboard> saveUserDashboardComponents(@RequestBody List<Dashboard> cards) {
        return this.dashboardService.saveUserDashboardComponents(cards);
    }

}
