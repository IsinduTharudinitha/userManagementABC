package com.Spring.UserManagementForABC.Controller;


import com.Spring.UserManagementForABC.Resources.UserResource;
import com.Spring.UserManagementForABC.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResource> getUser(@PathVariable("id") Long id){
        UserResource userResource = userService.getUserById(id);
        return ResponseEntity.ok(userResource);
    }

    @PostMapping("/add")
     public ResponseEntity<UserResource> createUser(UserResource userResource) {
        UserResource userResourceResult =userService.createUser(userResource);
        return ResponseEntity.ok(userResourceResult);
     }

     @PutMapping("/{id}")
    public ResponseEntity<UserResource> updateUser(@PathVariable("id") Long id, @RequestBody UserResource userResource){
        userResource.setId(id);
        UserResource userResourceResult = userService.updateUserById(userResource);
        return ResponseEntity.ok(userResourceResult);
     }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
     }


}
