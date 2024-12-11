package com.Spring.UserManagementForABC.Entity;


import com.Spring.UserManagementForABC.Enum.RoleType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String phoneNumber;


    @ManyToOne
    @JoinColumn(name = "role_name", referencedColumnName = "name")
    private Role role;


}
