package com.Spring.UserManagementForABC.Repository;

import com.Spring.UserManagementForABC.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
}
