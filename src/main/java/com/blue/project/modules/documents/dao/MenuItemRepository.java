package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, JpaSpecificationExecutor<MenuItem>, PagingAndSortingRepository<MenuItem, Long>, QueryByExampleExecutor<MenuItem> {
    List<MenuItem> findAllByMenu_IdOrderBySortAsc(Long menuId);
}
