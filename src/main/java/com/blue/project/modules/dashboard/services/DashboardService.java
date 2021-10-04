package com.blue.project.modules.dashboard.services;

import com.blue.project.modules.dashboard.models.Dashboard;
import com.blue.project.modules.dashboard.models.DashboardType;

import java.util.List;

public interface DashboardService {

    List<Dashboard> getUserDashboardComponents();

    List<DashboardType> getUserDashboardTypeAvailable();

    List<Dashboard> getUserAvailableDashboardComponents();

    List<Dashboard> saveUserDashboardComponents(List<Dashboard> cards);
}
