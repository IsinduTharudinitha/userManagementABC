package com.Spring.UserManagementForABC.Service;


import com.Spring.UserManagementForABC.Resources.UserResource;

public interface UserService {
    public UserResource createUser(UserResource userResource);
    public void deleteUser(Long id);
    public UserResource getUserById(Long id);
    public UserResource updateUserById(UserResource userResource);

}
