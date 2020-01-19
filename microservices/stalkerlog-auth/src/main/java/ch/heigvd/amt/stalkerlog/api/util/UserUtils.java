package ch.heigvd.amt.stalkerlog.api.util;

import ch.heigvd.amt.stalkerlog.api.model.User;
import ch.heigvd.amt.stalkerlog.entities.UserEntity;

/**
 * A helper containing simple conversions functions for users
 *
 * @author Alison Savary, Luc Wachter
 */
public class UserUtils {
    public static UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        // Hash password
        entity.setPassword(AuthUtils.hashPassword(user.getPassword()));
        return entity;
    }

    public static User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setPassword(entity.getPassword());
        return user;
    }
}
