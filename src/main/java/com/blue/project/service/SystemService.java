package com.blue.project.service;

import com.blue.project.modules.maintenance.dto.SystemInfo;
import com.blue.project.modules.maintenance.dto.SystemStatus;
import name.mymiller.nadia.system.SystemStatusReport;

import java.util.List;

public interface SystemService {
    SystemInfo getSystemInfo();

    List<SystemStatus> getSystemStatus();

    SystemStatusReport getCurrentSystemStatus();
}
