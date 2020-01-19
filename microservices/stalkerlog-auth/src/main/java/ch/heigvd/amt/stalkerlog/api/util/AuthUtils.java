package ch.heigvd.amt.stalkerlog.api.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

/**
 * A helper to manage authentication linked operations
 *
 * @author Alison Savary, Luc Wachter
 */
public class AuthUtils {
    private static Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createJWTString(Long userId, boolean role) {
        // Generate signed JWT containing the user's id and role
        return Jwts.builder()
            .claim("userId", userId)
            .claim("role", role)
            .signWith(key)
            .compact();
    }

    public static Jws<Claims> decodeJWTString(String jwt) {
        try {
            // Parse claims encoded in JWT
            return Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt);
        } catch (JwtException e) {
            // If exception was thrown, this JWT cannot be trusted
            return null;
        }
    }
}
