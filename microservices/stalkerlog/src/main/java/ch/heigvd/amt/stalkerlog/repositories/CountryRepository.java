package ch.heigvd.amt.stalkerlog.repositories;

import ch.heigvd.amt.stalkerlog.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository to generate operations on the country resource
 *
 * @author Alison Savary, Luc Wachter
 */
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
}
