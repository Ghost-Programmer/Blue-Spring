package com.blue.project.modules.dashboard.dao;

import com.blue.project.modules.dashboard.models.DashboardType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DashboardTypeRepository extends JpaRepository<DashboardType, Long>, JpaSpecificationExecutor<DashboardType> {
    List<DashboardType> findAllByDefaultItemIsTrue();
}
