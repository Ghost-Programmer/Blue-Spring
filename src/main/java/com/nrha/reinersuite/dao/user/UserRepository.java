package com.nrha.reinersuite.dao.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.nrha.reinersuite.models.users.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Boolean existsByUsername(String username);
    User findFirstByUsername(String username);
}
