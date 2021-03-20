package com.blue.project.modules.users.dao;

import com.blue.project.modules.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User> {

    Boolean existsByUsername(String username);

    User findFirstByUsername(String username);
}
