package com.Spring.UserManagementForABC.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class JWTUtils {
     private final SecretKey Key;
     private long expiration = 60 * 60 * 1000;


    public JWTUtils(){
         String secretString = "843567893696976453275974432697R634976R738467TR678T34865R6834R8763T478378637664538745673865783678548735687R3";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));
        this.Key = new SecretKeySpec(keyBytes, "HmacSHA256");
    }


    public String generateToken(String email, String role, long expirationTimeInMillis) {
        // Example JWT generation logic
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)  // Add role as a claim
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS512, Key)  // Use your secret key
                .compact();
    }

    // Generate Refresh Token
    public String generateRefreshToken(HashMap<String, Object> claims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(claims)
                .claim("role", userDetails.getAuthorities().stream()
                        .map(auth -> auth.getAuthority())
                        .collect(Collectors.joining(",")))
                .setSubject(userDetails.getUsername()) // Assuming email is the username
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, Key)
                .compact();
    }

    public String extractUserEmail(String token) {
        return extractClaims(token).getSubject(); // Subject is email
    }

    public Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token, String email) {
        String tokenEmail = extractUserEmail(token);
        return email.equals(tokenEmail) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }


}
