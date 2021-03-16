package com.blue.project.service;

import com.blue.project.dao.maintenance.ScheduleRepository;
import com.blue.project.dto.ScheduledMaintenance;
import com.blue.project.dto.mainteance.MaintenanceSearch;
import com.blue.project.models.maintenance.Scheduled;
import com.blue.project.models.users.User;
import name.mymiller.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
}
