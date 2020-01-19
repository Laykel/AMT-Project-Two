package ch.heigvd.amt.stalkerlog.api.endpoints;

import ch.heigvd.amt.stalkerlog.api.SessionApi;
import ch.heigvd.amt.stalkerlog.api.model.Credentials;
import ch.heigvd.amt.stalkerlog.repositories.UserRepository;
import ch.heigvd.amt.stalkerlog.services.UserService;
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
    UserService userService;

    @Override
    public ResponseEntity<String> getToken(@ApiParam(value = "", required = true) @Valid @RequestBody Credentials credentials) {
        try {
            String jwt = userService.createJWTString(credentials);
            return ResponseEntity.ok(jwt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
