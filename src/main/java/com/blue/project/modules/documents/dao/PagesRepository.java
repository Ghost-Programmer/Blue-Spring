package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface PagesRepository extends JpaRepository<Page, Long>, JpaSpecificationExecutor<Page>, PagingAndSortingRepository<Page, Long>, QueryByExampleExecutor<Page> {
    Page findPageByUuid(String uuid);
}
