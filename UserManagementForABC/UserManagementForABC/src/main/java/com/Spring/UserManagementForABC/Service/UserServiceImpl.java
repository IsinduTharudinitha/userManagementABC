package com.Spring.UserManagementForABC.Service;

import com.Spring.UserManagementForABC.Entity.User;
import com.Spring.UserManagementForABC.Repository.UserRepository;
import com.Spring.UserManagementForABC.Resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResource createUser(UserResource userResource) {

        User user = convertToUser(new User(),userResource);
        User saveduser = userRepository.save(user);
        return convertToUserResource(saveduser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserResource getUserById(Long id) {
      User user = getById(id);
      return convertToUserResource(user);

    }


    @Override
    public UserResource updateUserById(UserResource userResource) {
        User user = getById(userResource.getId());
        User updatedUser = convertToUser(user,userResource);
        User savedUser = userRepository.save(updatedUser);
        return convertToUserResource(savedUser);
    }


    private User getById(Long id){
         return userRepository.findById(id);
         //exeption should be handled

    }

    public UserResource convertToUserResource(User user) {
        return UserResource.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

    public User convertToUser(User user,UserResource userResource) {
                user.setFirstname(userResource.getFirstname());
                user.setLastname(userResource.getLastname());
                user.setEmail(userResource.getEmail());
                user.setPhoneNumber(userResource.getPhoneNumber());
                user.setRole(userResource.getRole());

                if(userResource.getPassword()!=null){
                    user.setPassword(userResource.getPassword());
                }

                return user;
    }
}
