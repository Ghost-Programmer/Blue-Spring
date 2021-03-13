package com.blue.project.dao.dashboard;

import com.blue.project.models.dashboard.Dashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Long>, JpaSpecificationExecutor<Dashboard> {
    List<Dashboard> findAllByUserIdOrderBySortAsc(Long userId);

    @Modifying
    @Query(value = "delete from dashboard.dashboard where id in (:idList)", nativeQuery = true)
    void deleteAllInIdList(@Param("idList") List<Long> idList);
}
