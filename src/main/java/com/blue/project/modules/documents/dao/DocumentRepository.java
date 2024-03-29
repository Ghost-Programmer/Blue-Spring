package com.blue.project.modules.documents.dao;

import com.blue.project.modules.documents.models.Document;
import com.blue.project.modules.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.time.ZonedDateTime;
import java.util.List;


public interface DocumentRepository extends JpaRepository<Document, Long>, JpaSpecificationExecutor<Document>, PagingAndSortingRepository<Document, Long>, QueryByExampleExecutor<Document> {

    Document findFirstByIdAndUser(Long id, User user);

    Document findFirstByUuid(String uuid);

    @Query(value = "select distinct(d.content_type) from documents.documents d " +
            " join user.user u on u.id = d.user_id " +
            "    where u.username like :username " +
            "    and d.filename like :filename " +
            "    and d.date_created < :dateCreated " +
            "    and d.size > :size " +
            "    and d.content_type like :contentType", nativeQuery = true)
    List<String> findAllContentTypesByDocSearch(@Param("username") String username,
                                                @Param("filename") String filename,
                                                @Param("contentType") String contentType,
                                                @Param("dateCreated") ZonedDateTime dateCreated,
                                                @Param("size") Long size);
}
