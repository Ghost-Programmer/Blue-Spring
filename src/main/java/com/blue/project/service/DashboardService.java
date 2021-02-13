package com.blue.project.service;

import com.blue.project.models.dashboard.Dashboard;
import com.blue.project.models.dashboard.DashboardType;

import java.util.List;

public interface DashboardService {

    List<Dashboard> getUserDashboardComponents();
    List<DashboardType> getUserDashboardTypeAvailable();
    List<Dashboard> getUserAvailableDashboardComponents();

    List<Dashboard> saveUserDashboardComponents(List<Dashboard> cards);
}
