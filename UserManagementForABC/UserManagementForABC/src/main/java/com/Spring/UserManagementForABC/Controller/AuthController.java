package com.Spring.UserManagementForABC.Controller;


import com.Spring.UserManagementForABC.Resources.AuthRequest;
import com.Spring.UserManagementForABC.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
