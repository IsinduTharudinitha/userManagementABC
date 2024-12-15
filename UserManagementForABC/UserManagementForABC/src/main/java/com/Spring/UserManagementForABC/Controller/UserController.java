package com.Spring.UserManagementForABC.Controller;


import com.Spring.UserManagementForABC.Resources.UserResource;
import com.Spring.UserManagementForABC.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserResource>> searchUsers(@RequestParam("query") String query) {
        List<UserResource> users = userService.searchUsers(query);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserResource> getUser(@PathVariable("id") Long id){
        UserResource userResource = userService.getUserById(id);
        return ResponseEntity.ok(userResource);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
     public ResponseEntity<UserResource> createUser(@RequestBody UserResource userResource) {
        UserResource userResourceResult =userService.createUser(userResource);
        return ResponseEntity.ok(userResourceResult);
     }

     @PutMapping("/{id}")
     @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResource> updateUser(@PathVariable("id") Long id, @RequestBody UserResource userResource){
        userResource.setId(id);
        UserResource userResourceResult = userService.updateUserById(userResource);
        return ResponseEntity.ok(userResourceResult);
     }

     @DeleteMapping("/{id}")
     @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
     }


}
