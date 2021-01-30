package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dto.ScheduledMaintenance;
import org.springframework.security.access.prepost.PreAuthorize;

public interface MaintenanceService {

    @PreAuthorize("permitAll()")
    ScheduledMaintenance getNextScheduledMaintenance();
}
