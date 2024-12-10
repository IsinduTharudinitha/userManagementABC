package com.Spring.UserManagementForABC.Resources;

import com.Spring.UserManagementForABC.Entity.Role;
import com.Spring.UserManagementForABC.Enum.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResource {
    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String phoneNumber;

    private Role role;


}
