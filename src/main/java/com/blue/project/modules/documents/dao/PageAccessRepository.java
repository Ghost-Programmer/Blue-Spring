package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.Page;
import com.blue.project.modules.documents.models.PageAccess;
import com.blue.project.modules.users.models.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface PageAccessRepository extends JpaRepository<PageAccess, Long>, JpaSpecificationExecutor<PageAccess>, PagingAndSortingRepository<PageAccess, Long>, QueryByExampleExecutor<PageAccess> {
    void deleteAllByPageId(Long pageId);
    List<PageAccess> findAllByPageId(Long pageId);
}
