package ch.heigvd.amt.stalkerlog.services;

import ch.heigvd.amt.stalkerlog.entities.UserEntity;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

/**
 * A service to manage user linked operations
 *
 * @author Alison Savary, Luc Wachter
 */
@Service
public class UserService implements IUserService {
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createJWTString(UserEntity user) {
        // Generate signed JWT containing the user's email
        String jws = Jwts.builder().setSubject(user.getEmail()).signWith(key).compact();

        return jws;
    }

    public String decodeJWTString(String jwt) {
        try {
            // Get email encoded in JWT
            String userEmail = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody().get("sub", String.class);
            return userEmail;
        } catch (JwtException e) {
            // If exception was thrown, this JWT cannot be trusted
            return null;
        }
    }
}
