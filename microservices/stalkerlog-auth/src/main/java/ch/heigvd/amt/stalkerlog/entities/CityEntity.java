package ch.heigvd.amt.stalkerlog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * JPA entity describing a city
 *
 * @author Alison Savary, Luc Wachter
 */
@Entity
@Table(name = "city")
public class CityEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Getter
    @Setter
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_country")
    @Getter
    @Setter
    private CountryEntity country;
}
