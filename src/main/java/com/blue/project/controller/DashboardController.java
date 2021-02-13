package com.blue.project.controller;

import com.blue.project.models.dashboard.Dashboard;
import com.blue.project.service.DashboardService;
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


    @RolesAllowed("ROLE_USER")
    @GetMapping("/components")
    public List<Dashboard> getUserDashboardComponents() {
        return this.dashboardService.getUserDashboardComponents();
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/components/list")
    public List<Dashboard> getUserDashboardTypeAvailable() {
        return this.dashboardService.getUserAvailableDashboardComponents();
    }

    @RolesAllowed("ROLE_USER")
    @PostMapping("/components")
    public List<Dashboard> saveUserDashboardComponents(@RequestBody List<Dashboard> cards) {
        return this.dashboardService.saveUserDashboardComponents(cards);
    }

}
