package com.blue.project.dao.user;

import com.blue.project.models.documents.Document;
import com.blue.project.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;


public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document>, PagingAndSortingRepository<Document, Long>, QueryByExampleExecutor<Document> {

    Document findFirstByIdAndUser(Long id, User user);
    Document findFirstByUuid(String uuid);;
}
