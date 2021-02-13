package com.blue.project.dao.dashboard;

import com.blue.project.models.dashboard.DashboardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DashboardTypeRepository extends JpaRepository<DashboardType, Long>, JpaSpecificationExecutor<DashboardType> {
    List<DashboardType> findAllByDefaultItemIsTrue();
}
