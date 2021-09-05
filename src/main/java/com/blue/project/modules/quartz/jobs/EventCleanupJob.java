package com.blue.project.modules.quartz.jobs;

import com.blue.project.modules.maintenance.dao.MaintenanceEventRepository;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.ZonedDateTime;

public class EventCleanupJob implements Job {

    @Autowired
    private MaintenanceEventRepository maintenanceEventRepository;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        this.logger.info("Starting Event Cleanup Job");
        this.maintenanceEventRepository.deleteAllByDateCreatedBefore(ZonedDateTime.now().minusWeeks(3));
        this.logger.info("Completed Event Cleanup Job");
    }
}
