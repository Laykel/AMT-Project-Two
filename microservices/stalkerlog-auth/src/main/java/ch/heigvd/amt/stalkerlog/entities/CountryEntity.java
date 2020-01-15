package ch.heigvd.amt.stalkerlog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * JPA entity describing a country
 *
 * @author Alison Savary, Luc Wachter
 */
@Entity
@Table(name = "country")
public class CountryEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String countryCode;
}
