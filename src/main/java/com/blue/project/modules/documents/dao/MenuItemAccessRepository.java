package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.MenuItem;
import com.blue.project.modules.documents.models.MenuItemAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface MenuItemAccessRepository extends JpaRepository<MenuItemAccess, Long>, JpaSpecificationExecutor<MenuItemAccess>, PagingAndSortingRepository<MenuItemAccess, Long>, QueryByExampleExecutor<MenuItemAccess> {
    List<MenuItemAccess>  findAllByMenuItemId(Long menuItemId);
};
