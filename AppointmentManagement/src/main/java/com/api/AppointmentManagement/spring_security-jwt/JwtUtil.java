package com.api.AppointmentManagement.spring_security-jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
    private static final String SECRET = "my-temporary-secret-key";
    private static final long EXPIRATION_MS = 1000 * 60 * 60;

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(UserDetails ud, String role) {
        return Jwts.builder()
            .setSubject(ud.getUsername()) // email
            .claim("role", role)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(getKey()).build()
                   .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token, UserDetails ud) {
        final String email = extractEmail(token);
        final Date exp = Jwts.parserBuilder().setSigningKey(getKey()).build()
                              .parseClaimsJws(token).getBody().getExpiration();
        return email.equals(ud.getUsername()) && exp.after(new Date());
    }
    
public Optional<UserEntity> getCurrentUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null && authentication.isAuthenticated()) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails customUser) {
            String email = customUser.getUsername();
            return userRepository.findByEmail(email);
        }
    }

    return Optional.empty();
}

    
}
