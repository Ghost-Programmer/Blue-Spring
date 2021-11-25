package com.blue.project.modules.quartz.jobs;

import com.blue.project.modules.maintenance.services.MaintenanceService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EventCleanupJob implements Job {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MaintenanceService maintenanceService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.logger.info("Starting Event Cleanup Job");
        this.maintenanceService.cleanupMaintenanceData();
        this.logger.info("Completed Event Cleanup Job");
    }
}
