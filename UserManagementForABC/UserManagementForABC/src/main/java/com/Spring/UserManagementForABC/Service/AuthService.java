package com.Spring.UserManagementForABC.Service;

import com.Spring.UserManagementForABC.Exception.SystemException;
import com.Spring.UserManagementForABC.Resources.AuthRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {
    void login(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AuthRequest loginRequest) throws SystemException;
    void logout(String accessToken) throws SystemException;
}
