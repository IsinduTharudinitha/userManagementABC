package com.Spring.UserManagementForABC.Service;


import com.Spring.UserManagementForABC.Entity.Permission;
import com.Spring.UserManagementForABC.Entity.Role;
import com.Spring.UserManagementForABC.Exception.ErrorCode;
import com.Spring.UserManagementForABC.Exception.SystemException;
import com.Spring.UserManagementForABC.Repository.PermissionRepository;
import com.Spring.UserManagementForABC.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }


    public Set<Permission> getPermissionsByRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new SystemException(ErrorCode.ROLE_NOT_FOUND));
        System.out.println(role);
        return role.getPermissions();
    }


    public Role updatePermissions(Long roleId, Set<Long> permissionIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new SystemException(ErrorCode.ROLE_NOT_FOUND));

        Set<Permission> permissions = new HashSet<>();
        for (Long permissionId : permissionIds) {
            Permission permission = permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new SystemException(ErrorCode.PERMISSION_NOT_FOUND));
            permissions.add(permission);
        }
        role.setPermissions(permissions);

        return roleRepository.save(role);
    }
}
