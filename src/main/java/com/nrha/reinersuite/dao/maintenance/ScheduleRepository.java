package com.nrha.reinersuite.dao.maintenance;

import com.nrha.reinersuite.models.maintenance.Scheduled;
import com.nrha.reinersuite.models.users.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.ZonedDateTime;
import java.util.List;

public interface ScheduleRepository  extends JpaRepository<Scheduled, Long>, JpaSpecificationExecutor<VerificationToken> {

    Scheduled findFirstByStartGreaterThanEqualOrderByStartAsc(ZonedDateTime zonedDateTime);
}
