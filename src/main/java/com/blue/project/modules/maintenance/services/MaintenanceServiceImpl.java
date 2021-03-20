package com.blue.project.modules.maintenance.services;

import com.blue.project.modules.maintenance.dao.ScheduleRepository;
import com.blue.project.modules.maintenance.dto.ScheduledMaintenance;
import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.maintenance.dto.MaintenanceSearch;
import com.blue.project.modules.maintenance.models.Scheduled;
import name.mymiller.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.Optional;

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

    public MaintenanceSearch search(MaintenanceSearch search) {
        Pageable pageable;
        Page<Scheduled> results;

        if (StringUtils.isNotNullOrEmpty(search.getSort())) {
            if (search.getAscending()) {
                pageable = PageRequest.of(search.getPage(), search.getSize(), Sort.by(search.getSort()).ascending());
            } else {
                pageable = PageRequest.of(search.getPage(), search.getSize(), Sort.by(search.getSort()).descending());
            }
        } else {
            pageable = PageRequest.of(search.getPage(), search.getSize());
        }

        results = this.scheduleRepository.findAll(pageable);

        search.setResults(results.toList());
        search.setTotal(results.getTotalElements());

        return search;
    }

    public StatusMessage saveScheduledMaintenance(Scheduled scheduled) {
        this.scheduleRepository.save(scheduled);
        return new StatusMessage().setOk(true);
    }

    public StatusMessage deleteScheduledMaintenance(Scheduled scheduled) {
        this.scheduleRepository.delete(scheduled);
        return new StatusMessage().setOk(true);
    }

    public StatusMessage deleteScheduledMaintenance(Long scheduledId) {
        Optional<Scheduled> scheduled = this.scheduleRepository.findById(scheduledId);
        if(scheduled.isPresent()) {
            return this.deleteScheduledMaintenance(scheduled.get());
        }
        return new StatusMessage().setOk(false).setMessage("Scheduled: " + scheduledId + " not found.");
    }
}
