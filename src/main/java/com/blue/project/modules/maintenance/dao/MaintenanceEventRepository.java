package com.blue.project.modules.maintenance.dao;

import com.blue.project.modules.maintenance.models.MaintenanceEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.ZonedDateTime;
import java.util.List;

public interface MaintenanceEventRepository extends JpaRepository<MaintenanceEvent, Long>, JpaSpecificationExecutor<MaintenanceEvent>, PagingAndSortingRepository<MaintenanceEvent, Long>, QueryByExampleExecutor<MaintenanceEvent> {

    List<MaintenanceEvent> findAllByDateCreatedAfter(ZonedDateTime dateCreated);
}
