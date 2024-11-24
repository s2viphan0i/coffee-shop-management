package com.csm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

public class JwtUtil {

    private static final String SECRET_KEY = "my_secret_key";
    private static final String ISSUER = "coffee-shop";
    public static final long TOKEN_EXPIRE_TIME = 3600000; // 1 hour

    private static final Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
    private static final JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();

    // Generate a JWT token
    public static String generateToken(UserDetails userDetails) {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(userDetails.getUsername())
                .withClaim("roles", userDetails.getAuthorities().stream().map(Object::toString).toList())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRE_TIME))
                .sign(algorithm);
    }


    // Validate the JWT token
    public static boolean validateToken(String token, String username) {
        return (username.equals(getUsername(token)) && !isTokenExpired(token));
    }

    // Extract username from the token
    public static String getUsername(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException ex) {
            // Log the error
            System.err.println("Unable to extract username: " + ex.getMessage());
            return null;
        }
    }

    // Check if the token is expired
    private static boolean isTokenExpired(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getExpiresAt().before(new Date());
        } catch (JWTVerificationException ex) {
            // Log the error
            System.err.println("Unable to extract token expired: " + ex.getMessage());
            return false;
        }
    }

    public static List<String> getRoles(String token) {
        try {
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getClaim("roles").asList(String.class);
        } catch (JWTVerificationException ex) {
            // Log the error
            System.err.println("Unable to extract role: " + ex.getMessage());
            return null;
        }
    }

}
