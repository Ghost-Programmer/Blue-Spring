package com.blue.project.modules.maintenance.services;

import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.calendar.services.CalendarServiceProviderInterface;
import com.blue.project.modules.maintenance.dto.MaintenanceSearch;
import com.blue.project.modules.maintenance.dto.ScheduledMaintenance;
import com.blue.project.modules.maintenance.models.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;


public interface MaintenanceService extends CalendarServiceProviderInterface {

    @PreAuthorize("permitAll()")
    ScheduledMaintenance getNextScheduledMaintenance();

    MaintenanceSearch search(MaintenanceSearch search);

    Scheduled saveScheduledMaintenance(Scheduled scheduled);

    StatusMessage deleteScheduledMaintenance(Scheduled scheduled);

    StatusMessage deleteScheduledMaintenance(Long scheduledId);

    void cleanupMaintenanceData();

    StatusMessage setLoggingLevel(String level);
}
