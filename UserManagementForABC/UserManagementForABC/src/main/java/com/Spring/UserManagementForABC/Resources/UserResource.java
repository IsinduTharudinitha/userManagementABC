package com.Spring.UserManagementForABC.Resources;

import com.Spring.UserManagementForABC.Entity.Role;
import lombok.*;


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

    private String role;

    public UserResource() {
        this.id = null;
        this.firstname = null;
        this.lastname = null;
        this.email = null;
        this.password = null;
        this.phoneNumber = null;
        this.role = null;
    }


    private UserResource(Builder builder) {
        if (builder != null) {
            this.id = builder.id;
            this.firstname = builder.firstname;
            this.lastname = builder.lastname;
            this.email = builder.email;
            this.phoneNumber = builder.phoneNumber;
            this.role = builder.role;
        }
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public static class Builder {
        private Long id;
        private String firstname;
        private String lastname;
        private String email;
        private String phoneNumber;
        private String role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder firstname(String firstname) {
            this.firstname = firstname;
            return this;
        }

        public Builder lastname(String lastname) {
            this.lastname = lastname;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder role(String role) {
            this.role = role;
            return this;
        }

        public UserResource build() {
            return new UserResource(this);
        }
    }

    @Override
    public String toString() {
        return "UserResource{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + (role != null ? role : "null") +
                '}';
    }



}
