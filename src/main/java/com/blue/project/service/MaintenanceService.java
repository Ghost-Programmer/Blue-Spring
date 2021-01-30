package com.blue.project.service;

import com.blue.project.dto.ScheduledMaintenance;
import org.springframework.security.access.prepost.PreAuthorize;

public interface MaintenanceService {

    @PreAuthorize("permitAll()")
    ScheduledMaintenance getNextScheduledMaintenance();
}
