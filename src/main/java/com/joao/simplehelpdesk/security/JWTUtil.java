//package com.joao.simplehelpdesk.security;
//
//import java.nio.charset.StandardCharsets;
//import java.security.Key;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//
//@Component
//public class JWTUtil {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Long expiration;
//
//    private Key getSigningKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
//    }
//
//    public String generateToken(String email) {
//        return Jwts.builder()
//            .setSubject(email)
//            .setExpiration(new Date(System.currentTimeMillis() + expiration))
//            .signWith(getSigningKey(), SignatureAlgorithm.HS512)
//            .compact();
//    }
//
//    public boolean tokenValido(String token) {
//        Claims claims = getClaims(token);
//        if (claims != null) {
//            String username = claims.getSubject();
//            Date expirationDate = claims.getExpiration();
//            Date now = new Date(System.currentTimeMillis());
//            return username != null && expirationDate != null && now.before(expirationDate);
//        }
//        return false;
//    }
//
//    private Claims getClaims(String token) {
//        try {
//            return Jwts.parserBuilder()
//                .setSigningKey(getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    public String getUsername(String token) {
//        Claims claims = getClaims(token);
//        if (claims != null) {
//            return claims.getSubject();
//        }
//        return null;
//    }
//}
