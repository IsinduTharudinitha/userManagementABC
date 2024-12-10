package com.Spring.UserManagementForABC.Repository;

import com.Spring.UserManagementForABC.Entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

}
