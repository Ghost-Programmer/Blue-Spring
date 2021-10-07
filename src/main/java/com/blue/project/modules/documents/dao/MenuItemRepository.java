package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.Menu;
import com.blue.project.modules.documents.models.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface MenuItemRepository  extends JpaRepository<MenuItem, Long>, JpaSpecificationExecutor<MenuItem>, PagingAndSortingRepository<MenuItem, Long>, QueryByExampleExecutor<MenuItem> {
}
