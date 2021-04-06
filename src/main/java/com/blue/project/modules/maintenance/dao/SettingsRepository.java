package com.blue.project.modules.maintenance.dao;

import com.blue.project.modules.maintenance.models.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;
import java.util.List;

public interface SettingsRepository extends JpaRepository<Setting, Long>, JpaSpecificationExecutor<Setting> {

    @Query(value ="select * from maintenance.settings s where s.name = :name and s.start_date_time > = :date and (s.end_date_time is null or s.end_date_time <= :date)", nativeQuery = true)
    List<Setting> findAllByNameAndDate(@Param("name")String name, @Param("date")ZonedDateTime date);

    @Query(value ="select * from maintenance.settings s where s.name = :name", nativeQuery = true)
    List<Setting> findAllByName(@Param("name")String name);

}
