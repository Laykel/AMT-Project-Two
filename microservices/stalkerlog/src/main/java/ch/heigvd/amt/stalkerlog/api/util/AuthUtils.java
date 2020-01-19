package ch.heigvd.amt.stalkerlog.api.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

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
