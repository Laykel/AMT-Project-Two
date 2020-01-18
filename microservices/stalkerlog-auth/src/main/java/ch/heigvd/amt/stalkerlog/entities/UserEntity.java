package ch.heigvd.amt.stalkerlog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * JPA entity describing a user
 *
 * @author Alison Savary, Luc Wachter
 */
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {
    @Id
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private boolean isAdmin;
}
