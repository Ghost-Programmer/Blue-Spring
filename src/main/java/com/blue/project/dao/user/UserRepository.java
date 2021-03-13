package com.blue.project.dao.user;

import com.blue.project.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>, PagingAndSortingRepository<User, Long>, QueryByExampleExecutor<User> {

    Boolean existsByUsername(String username);

    User findFirstByUsername(String username);
}
