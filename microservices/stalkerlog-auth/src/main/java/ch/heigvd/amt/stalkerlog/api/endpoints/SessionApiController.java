package ch.heigvd.amt.stalkerlog.api.endpoints;

import ch.heigvd.amt.stalkerlog.api.SessionApi;
import ch.heigvd.amt.stalkerlog.api.model.Credentials;
import ch.heigvd.amt.stalkerlog.api.util.AuthUtils;
import ch.heigvd.amt.stalkerlog.entities.UserEntity;
import ch.heigvd.amt.stalkerlog.repositories.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * Spring controller to implement operations generated by codegen
 *
 * @author Alison Savary, Luc Wachter
 */
@Controller
@Api(tags = "session")
public class SessionApiController implements SessionApi {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<String> getToken(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials credentials) {
        UserEntity user = userRepository.findByEmail(credentials.getEmail());

        // Check user password
        if (!AuthUtils.checkPassword(credentials.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Wrong password");
        }

        // Create the JWT token and send it back as JSON
        String jwt = AuthUtils.createJWTString(user.getId(), user.isAdmin());
        return ResponseEntity.ok("{\"token\": \"" + jwt + "\"}");
    }
}