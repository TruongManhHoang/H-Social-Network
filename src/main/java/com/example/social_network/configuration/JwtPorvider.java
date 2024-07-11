package com.example.social_network.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtPorvider {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication authentication){

        String jwt = Jwts.builder()
                .setIssuer("JwtToken")
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", authentication.getName())
                .signWith(key)
                .compact();
        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt){
        // Bearer token
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey(key).build()
                .parseClaimsJws(jwt)
                .getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }
}
