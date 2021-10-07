package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MenuRepository  extends JpaRepository<Menu, Long>, JpaSpecificationExecutor<Menu>, PagingAndSortingRepository<Menu, Long>, QueryByExampleExecutor<Menu> {
}
