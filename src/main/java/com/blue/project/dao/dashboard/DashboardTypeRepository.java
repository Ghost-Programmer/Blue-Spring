package com.nrha.reinersuite.dao.dashboard;

import com.nrha.reinersuite.models.dashboard.DashboardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DashboardTypeRepository extends JpaRepository<DashboardType, Long>, JpaSpecificationExecutor<DashboardType> {
}
