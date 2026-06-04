/*
package com.sm.wellnow.config;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class JwtUtils {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);
        return null;
    }

    public String generateToken(String userId, String role) {
        return Jwts.builder()
                .subject(userId)
                .claim("roles", List.of(role))
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(key())
                .compact();
    }

    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser().verifyWith((SecretKey) key()).build()
                    .parseSignedClaims(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserIdFromToken(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key())
                .build().parseSignedClaims(jwt)
                .getPayload().getSubject();
    }

    public Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key())
                .build().parseSignedClaims(jwt).getPayload();
    }

}
*/



package com.sm.wellnow.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    /*
     * Reads secret key from application.yml
     *
     * application.yml
     *
     * jwt:
     *   secret: yourBase64EncodedSecretKey
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /*
     * JWT expiration time in milliseconds
     *
     * Example:
     * 3600000 = 1 hour
     */
    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    /*
     * Extract JWT token from Authorization Header
     *
     * Example Header:
     * Authorization: Bearer eyJhbGc...
     */
    public String getJwtFromHeader(HttpServletRequest request) {

        // Get Authorization header
        String bearerToken = request.getHeader("Authorization");

        /*
         * Check:
         * 1. Header is not null
         * 2. Header starts with "Bearer "
         */
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {

            // Remove "Bearer " and return actual token
            return bearerToken.substring(7);
        }

        return null;
    }

    /*
     * Generate JWT Token
     *
     * Parameters:
     * userId -> logged-in user id/email
     * role   -> USER / ADMIN
     */
    public String generateToken(String userId, String role) {

        return Jwts.builder()

                // Subject = main identity of user
                .subject(userId)

                // Custom claim
                .claim("roles", List.of(role))

                // Token creation time
                .issuedAt(new Date())

                // Token expiration time
                .expiration(
                        new Date(
                                new Date().getTime() + jwtExpirationMs
                        )
                )

                // Sign token using secret key
                .signWith(key())

                // Generate final compact JWT string
                .compact();
    }

    /*
     * Validate JWT Token
     *
     * Checks:
     * - Signature valid?
     * - Token modified?
     * - Expired?
     */
    public boolean validateJwtToken(String jwtToken) {

        try {

            /*
             * Parse and verify token
             */
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(jwtToken);

            return true;

        } catch (Exception e) {

            /*
             * Token invalid
             */
            e.printStackTrace();
        }

        return false;
    }

    /*
     * Create Secret Key
     *
     * Converts Base64 secret string into HMAC SHA key
     */
    private Key key() {

        return Keys.hmacShaKeyFor(

                // Decode Base64 secret
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    /*
     * Extract UserId / Subject from JWT
     *
     * Example:
     * sub = shivam@gmail.com
     */
    public String getUserIdFromToken(String jwt) {

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()

                // Parse JWT
                .parseSignedClaims(jwt)

                // Get payload
                .getPayload()

                // Get subject
                .getSubject();
    }

    /*
     * Extract all claims from JWT
     *
     * Claims Example:
     * {
     *   sub: "shivam@gmail.com",
     *   roles: ["USER"],
     *   exp: 12345678
     * }
     */
    public Claims getAllClaims(String jwt) {

        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(jwt)

                // Return payload claims
                .getPayload();
    }
}

