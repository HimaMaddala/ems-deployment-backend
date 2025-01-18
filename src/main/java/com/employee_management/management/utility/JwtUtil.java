package com.employee_management.management.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = Base64.getEncoder().encodeToString("#@ems_secret_key89+#".getBytes());

    public String generateToken(String username, String role,String email,String userId) {
        try {
            Map<String,Object> claims = new HashMap<>();
            claims.put("username",username);
            claims.put("email",email);
            claims.put("userId",userId);
            long thirtyDaysInMillis = 1000L * 60 * 60 * 24 * 10;
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .claim("role", role)
                    .setExpiration(new Date(System.currentTimeMillis() + thirtyDaysInMillis))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        } catch (Exception e) {
            System.out.println("Error generating token: " + e.getMessage());
            throw e;
        }
    }

    public Claims extractClaims(String token){
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public boolean isTokenValid(String token,String username){
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
}
