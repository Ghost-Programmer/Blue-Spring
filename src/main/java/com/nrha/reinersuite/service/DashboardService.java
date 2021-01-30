package com.nrha.reinersuite.service;

import com.nrha.reinersuite.models.dashboard.Dashboard;
import com.nrha.reinersuite.models.dashboard.DashboardType;

import java.util.List;

public interface DashboardService {

    List<Dashboard> getUserDashboardComponents();

    List<DashboardType> getUserDashboardTypeAvailable();
}
