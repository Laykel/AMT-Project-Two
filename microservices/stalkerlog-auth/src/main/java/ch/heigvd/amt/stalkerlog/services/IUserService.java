package ch.heigvd.amt.stalkerlog.services;

import ch.heigvd.amt.stalkerlog.api.model.Credentials;

/**
 * A service interface to manage user linked operations
 *
 * @author Alison Savary, Luc Wachter
 */
public interface IUserService {
    String createJWTString(Credentials user);

    String decodeJWTString(String jwt);
}
