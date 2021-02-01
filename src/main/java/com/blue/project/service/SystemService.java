package com.blue.project.service;

import com.blue.project.dto.system.SystemInfo;
import com.blue.project.dto.system.SystemStatus;

import java.util.List;

public interface SystemService {
    SystemInfo getSystemInfo();
    List<SystemStatus> getSystemStatus();
}
