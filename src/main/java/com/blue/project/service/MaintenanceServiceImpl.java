package com.blue.project.service;

import com.blue.project.dao.maintenance.ScheduleRepository;
import com.blue.project.dto.ScheduledMaintenance;
import com.blue.project.models.maintenance.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;

@Service
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public ScheduledMaintenance getNextScheduledMaintenance() {
        Scheduled maintenance = this.scheduleRepository.findFirstByStartGreaterThanEqualOrderByStartAsc(ZonedDateTime.now());

        if (maintenance == null) {
            return null;
        } else return new ScheduledMaintenance(maintenance);
    }
}
