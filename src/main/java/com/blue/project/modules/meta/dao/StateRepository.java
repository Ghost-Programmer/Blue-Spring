package com.blue.project.modules.meta.dao;

import com.blue.project.modules.meta.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StateRepository extends JpaRepository<State, Long>, JpaSpecificationExecutor<State> {
}
