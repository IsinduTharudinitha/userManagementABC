package com.Spring.UserManagementForABC.Service;

import com.Spring.UserManagementForABC.Enum.TokenType;
import com.Spring.UserManagementForABC.Exception.ErrorCode;
import com.Spring.UserManagementForABC.Exception.SystemException;
import com.Spring.UserManagementForABC.Resources.AuthRequest;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {


    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtils jwtUtils;

    @Value("${application.security.access-token-expiration-in-minutes}")
    private Integer accessTokenExpirationInMinutes;


    @Override
    public void login(HttpServletRequest servletRequest, HttpServletResponse servletResponse, AuthRequest loginRequest) throws SystemException {
        if (isNullOrEmpty(loginRequest.getEmail()) || isNullOrEmpty(loginRequest.getPassword())) {
            throw new SystemException(ErrorCode.INVALID_CREDENTIALS);
        }

        try {
            // Authenticate user with email and password
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getPassword()
                    )
            );

            // If authentication is successful, generate JWT token
            if (isAuthenticated(authentication)) {
                String role = authentication.getAuthorities().stream()
                        .findFirst()
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .orElseThrow(() -> new SystemException(ErrorCode.INVALID_CREDENTIALS));

                long accessTokenExpirationInMillis = accessTokenExpirationInMinutes * 1000L * 60L;

                // Generate token with email and role
                String accessToken = jwtUtils.generateToken(
                        loginRequest.getEmail(),
                        role,
                        accessTokenExpirationInMillis
                );

                // Add token to the response as a cookie
                servletResponse.addCookie(createCookie(TokenType.ACCESS_TOKEN.name(), accessToken, accessTokenExpirationInMinutes * 60));

                return;
            }

            throw new SystemException(ErrorCode.INVALID_CREDENTIALS);
        } catch (Exception ex) {
            // Catching any authentication exception and throwing custom error
            throw new SystemException(ErrorCode.LOGIN_FAILED);
        }

    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isAuthenticated(Authentication authentication) {
        return authentication != null && authentication.isAuthenticated();
    }

    private Cookie createCookie(String tokenType, String tokenValue, int expiryInSeconds) {
        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie(tokenType, tokenValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);  // Make sure to set this to true in production
        cookie.setMaxAge(expiryInSeconds);
        cookie.setPath("/");  // Set path to '/' so cookie is sent for all requests
        return cookie;
    }
}