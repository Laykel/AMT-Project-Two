package ch.heigvd.amt.stalkerlog.services;

import ch.heigvd.amt.stalkerlog.api.endpoints.UsersApiController;
import ch.heigvd.amt.stalkerlog.api.model.Credentials;
import ch.heigvd.amt.stalkerlog.api.model.User;
import ch.heigvd.amt.stalkerlog.entities.UserEntity;
import ch.heigvd.amt.stalkerlog.repositories.UserRepository;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;

/**
 * A service to manage user linked operations
 *
 * @author Alison Savary, Luc Wachter
 */
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String createJWTString(Credentials credentials) {
        UserEntity user = UsersApiController.toUserEntity(userRepository.findByEmail(credentials.getEmail()));

        if (!user.getPassword().equals(credentials.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        // Generate signed JWT containing the user's id
        return Jwts.builder().setSubject(Long.toString(user.getId())).signWith(key).compact();
    }

    public String decodeJWTString(String jwt) {
        try {
            // Get user id encoded in JWT
            return Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody().get("sub", String.class);
        } catch (JwtException e) {
            // If exception was thrown, this JWT cannot be trusted
            return null;
        }
    }
}
