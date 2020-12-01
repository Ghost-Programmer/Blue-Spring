package com.nrha.reinersuite.service;

import com.nrha.reinersuite.dao.maintenance.ScheduleRepository;
import com.nrha.reinersuite.dto.ScheduledMaintenance;
import com.nrha.reinersuite.models.maintenance.Scheduled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private ScheduleRepository scheduleRepository;


    @Override
    public ScheduledMaintenance getNextScheduledMaintenance() {
        Scheduled maintenance = this.scheduleRepository.findFirstByStartGreaterThanEqualOrderByStartAsc(ZonedDateTime.now());

        if(maintenance == null) {
            return null;
        } else return new ScheduledMaintenance(maintenance);
    }
}
