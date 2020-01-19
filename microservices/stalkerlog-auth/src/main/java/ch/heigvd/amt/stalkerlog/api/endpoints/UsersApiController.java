package ch.heigvd.amt.stalkerlog.api.endpoints;

import ch.heigvd.amt.stalkerlog.api.UsersApi;
import ch.heigvd.amt.stalkerlog.api.model.User;
import ch.heigvd.amt.stalkerlog.entities.UserEntity;
import ch.heigvd.amt.stalkerlog.repositories.UserRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Spring controller to implement operations generated by codegen
 *
 * @author Alison Savary, Luc Wachter
 */
@Controller
@Api(tags = "users")
public class UsersApiController implements UsersApi {
    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Void> putUser(Integer id, @Valid User user) {
        UserEntity userEntity = toUserEntity(user);

        // User exists in database
        if(userRepository.findById(Long.valueOf(id)).isPresent()) {
            // TODO check owner
            // TODO PROBABLY NOT HOW IT SHOULD BE DONE
            userEntity.setId(id);
            userRepository.save(userEntity);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<User> getUser(Integer id) {
        Optional<UserEntity> userEntity = userRepository.findById(Long.valueOf(id));

        // TODO Check owner
        if (userEntity.isPresent()) {
            User user = toUser(userEntity.get());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(Integer id) {
        if(userRepository.findById(Long.valueOf(id)).isPresent()) {
            // TODO check owner
            userRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public static UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword());
        return user;
    }
}
