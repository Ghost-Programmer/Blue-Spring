package com.nrha.reinersuite.dao.maintenance;

import com.nrha.reinersuite.models.maintenance.Scheduled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.ZonedDateTime;

public interface ScheduleRepository  extends JpaRepository<Scheduled, Long>, JpaSpecificationExecutor<Scheduled> {

    Scheduled findFirstByStartGreaterThanEqualOrderByStartAsc(ZonedDateTime zonedDateTime);
}
