package com.Spring.UserManagementForABC.Repository;

import com.Spring.UserManagementForABC.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
}
