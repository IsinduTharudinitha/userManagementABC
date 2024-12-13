package com.Spring.UserManagementForABC.Service;

import com.Spring.UserManagementForABC.Entity.Role;
import com.Spring.UserManagementForABC.Entity.User;
import com.Spring.UserManagementForABC.Exception.ErrorCode;
import com.Spring.UserManagementForABC.Exception.SystemException;
import com.Spring.UserManagementForABC.Repository.RoleRepository;
import com.Spring.UserManagementForABC.Repository.UserRepository;
import com.Spring.UserManagementForABC.Resources.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RoleRepository roleRepository;

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Constructor injection
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<UserResource> searchUsers(String query) {
        List<User> users = userRepository.searchByQuery(query);
        // Convert entities to resources
        return users.stream()
                .map(user -> new UserResource.Builder()
                        .id(user.getId())
                        .firstname(user.getFirstname())
                        .lastname(user.getLastname())
                        .email(user.getEmail())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserResource createUser(UserResource userResource) throws  SystemException {
        if(userRepository.existsByEmail(userResource.getEmail())) {
            throw new SystemException(ErrorCode.EMAIL_ALREADY_IN_USE);
        }
        System.out.println(userResource);
        User user = convertToUser(new User(),userResource);
//        System.out.println(user);
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
    public UserResource updateUserById(UserResource userResource) throws SystemException{
        User user = getById(userResource.getId());

        if(userRepository.existsByEmailAndIdNot(userResource.getEmail(),userResource.getId())){
            throw new SystemException(ErrorCode.EMAIL_ALREADY_IN_USE);
        }

        User updatedUser = convertToUser(user,userResource);
        User savedUser = userRepository.save(updatedUser);
        return convertToUserResource(savedUser);
    }


    private User getById(Long id){
         return userRepository.findById(id)
                 .orElseThrow(() -> new SystemException(ErrorCode.USER_NOT_FOUND));


    }

    public UserResource convertToUserResource(User user) {
        return new UserResource.Builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole().getName())
                .build();
    }

    public User convertToUser(User user,UserResource userResource) {
                user.setFirstname(userResource.getFirstname());
                user.setLastname(userResource.getLastname());
                user.setEmail(userResource.getEmail());
                user.setPhoneNumber(userResource.getPhoneNumber());

                Role role = roleRepository.findByName(userResource.getRole())
                      .orElseThrow(() -> new SystemException(ErrorCode.ROLE_NOT_FOUND)); // Handle role not found

                user.setRole(role);

                if(userResource.getPassword()!=null){
                    user.setPassword(passwordEncoder.encode(userResource.getPassword()));
                }

                return user;
    }
}
