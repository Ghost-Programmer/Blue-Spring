package com.blue.project.modules.maintenance.dao;

import com.blue.project.modules.maintenance.models.Scheduled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.ZonedDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Scheduled, Long>, JpaSpecificationExecutor<Scheduled> {

    Scheduled findFirstByStartGreaterThanEqualOrderByStartAsc(ZonedDateTime zonedDateTime);
    List<Scheduled> findAllByStartBetween(ZonedDateTime start, ZonedDateTime end);

}
