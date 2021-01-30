package com.blue.project.dao.dashboard;

import com.blue.project.models.dashboard.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>, JpaSpecificationExecutor<Dashboard> {
    List<Dashboard> findAllByUserIdOrderBySortAsc(Long userId);
}
