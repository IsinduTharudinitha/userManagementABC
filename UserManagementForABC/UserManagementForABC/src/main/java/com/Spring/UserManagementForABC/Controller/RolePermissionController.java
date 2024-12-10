package com.Spring.UserManagementForABC.Controller;

import com.Spring.UserManagementForABC.Entity.Permission;
import com.Spring.UserManagementForABC.Entity.Role;
import com.Spring.UserManagementForABC.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/roles")
public class RolePermissionController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }


    @GetMapping("/{roleId}/permissions")
    public ResponseEntity<Set<Permission>> getPermissionsByRole(@PathVariable Long roleId) {
        Set<Permission> permissions = roleService.getPermissionsByRole(roleId);
        return ResponseEntity.ok(permissions);
    }


    @PutMapping("/{roleId}/permissions")
    public ResponseEntity<Role> updatePermissions(
            @PathVariable Long roleId,
            @RequestBody Set<Long> permissionIds) {
        Role updatedRole = roleService.updatePermissions(roleId, permissionIds);
        return ResponseEntity.ok(updatedRole);
    }
}
