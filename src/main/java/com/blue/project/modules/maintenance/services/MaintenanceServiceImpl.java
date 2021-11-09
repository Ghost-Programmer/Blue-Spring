package com.blue.project.modules.maintenance.services;

import ch.qos.logback.classic.Level;
import com.blue.project.modules.calendar.annontation.CalendarServiceProvider;
import com.blue.project.modules.calendar.dto.EventContext;
import com.blue.project.modules.calendar.dto.EventData;
import com.blue.project.modules.maintenance.dao.MaintenanceEventRepository;
import com.blue.project.modules.maintenance.dao.ScheduleRepository;
import com.blue.project.modules.maintenance.dto.ScheduledMaintenance;
import com.blue.project.dto.StatusMessage;
import com.blue.project.modules.maintenance.dto.MaintenanceSearch;
import com.blue.project.modules.maintenance.models.Scheduled;
import name.mymiller.utils.ListUtils;
import name.mymiller.utils.StringUtils;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CalendarServiceProvider
@Service
@Transactional
public class MaintenanceServiceImpl implements MaintenanceService {

    public static final String ORGANIZATION = "The Jockey Club";
    public static final String SYSTEM_MAINTENANCE = "System Maintenance";
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private MaintenanceEventRepository maintenanceEventRepository;

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

    public Scheduled saveScheduledMaintenance(Scheduled scheduled) {
        return this.scheduleRepository.save(scheduled);
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

    @Override
    public List<EventData> getEventData(ZonedDateTime start, ZonedDateTime end) {
        List<Scheduled> maintenanceList = ListUtils.safe(this.scheduleRepository.findAllByStartBetween(start, end));
        return maintenanceList.stream()
                .map(com.blue.project.modules.maintenance.dto.EventData::new)
                .peek(eventData -> {
                    eventData.setOrganization(ORGANIZATION);
                    eventData.setType(SYSTEM_MAINTENANCE);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<EventContext> getEventContext() {
        List<EventContext> contextList = new ArrayList<>();
        EventContext context = new EventContext();
        context.setOrganization(ORGANIZATION);
        context.setType(SYSTEM_MAINTENANCE);
        contextList.add(context);

        return contextList;
    }

    @Override
    public void cleanupMaintenanceData() {
        this.maintenanceEventRepository.deleteAllByDateCreatedBefore(ZonedDateTime.now().minusWeeks(3));
    }

    public StatusMessage setLoggingLevel(String level) {
        Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);

        switch (level) {
            case "warn" -> root.setLevel(Level.WARN);
            case "info" -> root.setLevel(Level.INFO);
            case "error" -> root.setLevel(Level.ERROR);
            case "debug" -> root.setLevel(Level.DEBUG);
            case "trace" -> root.setLevel(Level.TRACE);
        }
        return new StatusMessage().setOk(true);
    }
}
