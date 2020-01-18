package ch.heigvd.amt.stalkerlog.services;

import ch.heigvd.amt.stalkerlog.entities.UserEntity;

/**
 * A service interface to manage user linked operations
 *
 * @author Alison Savary, Luc Wachter
 */
public interface IUserService {
    String createJWTString(UserEntity user);

    String decodeJWTString(String jwt);
}
