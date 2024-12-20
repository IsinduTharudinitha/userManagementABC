package com.Spring.UserManagementForABC.Controller;


import com.Spring.UserManagementForABC.Resources.AuthRequest;
import com.Spring.UserManagementForABC.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            HttpServletRequest servletRequest,
            HttpServletResponse servletResponse,
            @RequestBody AuthRequest request
    ) {
        authService.login(servletRequest, servletResponse, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authorizationHeader.substring(7); // Extract the token
        authService.logout(token);

        return ResponseEntity.ok().build();
    }


}
