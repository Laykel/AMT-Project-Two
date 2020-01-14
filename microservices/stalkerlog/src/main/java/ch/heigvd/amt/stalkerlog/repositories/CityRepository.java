package ch.heigvd.amt.stalkerlog.repositories;

import ch.heigvd.amt.stalkerlog.entities.CityEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository to generate operations on the city resource
 *
 * @author Alison Savary, Luc Wachter
 */
public interface CityRepository extends CrudRepository<CityEntity, Long> {
}
