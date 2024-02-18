package com.haven.security;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class JwtUtil {
	
	public static String generateKey() {
		 // Generate a secure 128-bit (16-byte) secret key
        byte[] keyBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);

        // Convert the byte array to a Base64-encoded string
        String secretKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated Secret Key: " + secretKey);
        return secretKey;
	}
	
    private static final String SECRET_KEY = generateKey(); // Replace with your secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds

    public static String generateToken(String email) {
        return Jwts.builder()
            .setSubject(email)

            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public static String extractUseremail(String token) {
        String name= Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
        System.out.println("Username : "+name);
        return name;
    }
    
    public static int extractUserId(String token) {
        try {
            Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
            int userId = claims.get("userId", Integer.class);
            System.out.println("User ID: " + userId);
            return userId;
        } catch (SignatureException e) {
            // Handle token validation exception
            return -1; 
        }
    }

    public static boolean isTokenValid(String token) {
    	try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
