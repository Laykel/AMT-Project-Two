package ch.heigvd.amt.stalkerlog.api.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.mindrot.jbcrypt.BCrypt;

import java.security.Key;
import java.util.Base64;

/**
 * A helper to manage authentication linked operations (JWT, bcrypt...)
 *
 * @author Alison Savary, Luc Wachter
 */
public class AuthUtils {
    private static String KEY_STRING = "+UekP+1B79335qjAe56sWOMZpI+ynYMhK3Dp8R+wS70=";

    /**
     * Generate and return a JWT compact string containing the claims passed as param
     *
     * @param userId The is of the logged in user
     * @param role   Whether the user is admin or not
     * @return The JWT string
     */
    public static String createJWTString(Long userId, boolean role) {
        // Generate signed JWT containing the user's id and role
        return Jwts.builder()
            .claim("userId", userId)
            .claim("role", role)
            .signWith(decodeKey(KEY_STRING))
            .compact();
    }

    /**
     * Decode a JWT compact string and return its claims
     *
     * @param jwt The compact JWT string
     * @return The claims contained in the token, null if the token is invalid
     */
    public static Jws<Claims> decodeJWTString(String jwt) {
        try {
            // Parse claims encoded in JWT
            return Jwts.parser()
                .setSigningKey(decodeKey(KEY_STRING))
                .parseClaimsJws(jwt);
        } catch (JwtException e) {
            // If exception was thrown, this JWT cannot be trusted
            return null;
        }
    }

    /**
     * Hash a plain text password using Bcrypt and a salt
     *
     * @param plainTextPassword The password to hash
     * @return The hashed password
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Check if a plain text password matches a hashed password
     *
     * @param plainTextPassword The plain text password from the request
     * @param hashedPassword    The hashed password from the DB
     * @return True if the hashes match, false otherwise
     */
    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(plainTextPassword, hashedPassword);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Convert a string representation of a HMAC-SHA 256 Key into a Key object
     *
     * @param keyString The string representation of the key
     * @return The corresponding Key
     */
    private static Key decodeKey(String keyString) {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
