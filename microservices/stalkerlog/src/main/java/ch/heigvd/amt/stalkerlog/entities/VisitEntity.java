package ch.heigvd.amt.stalkerlog.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import org.joda.time.LocalDate;

/**
 * JPA entity describing a visit
 *
 * @author Alison Savary, Luc Wachter
 */
@Entity
@Table(name = "visit")
public class VisitEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private LocalDate startDate;

    @Getter
    @Setter
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_star")
    @Getter
    @Setter
    private StarEntity star;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_city")
    @Getter
    @Setter
    private CityEntity city;

    @Getter
    @Setter
    private long owner;
}
