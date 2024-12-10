package com.Spring.UserManagementForABC.Service;


import com.Spring.UserManagementForABC.Exception.SystemException;
import com.Spring.UserManagementForABC.Resources.UserResource;

import java.util.List;

public interface UserService {
    public List<UserResource> searchUsers(String query);
    public UserResource createUser(UserResource userResource) throws SystemException;
    public void deleteUser(Long id);
    public UserResource getUserById(Long id);
    public UserResource updateUserById(UserResource userResource) throws SystemException;

}
