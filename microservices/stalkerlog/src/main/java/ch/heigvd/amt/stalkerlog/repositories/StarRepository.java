package ch.heigvd.amt.stalkerlog.repositories;

import ch.heigvd.amt.stalkerlog.entities.StarEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository to generate operations on the star resource
 *
 * @author Alison Savary, Luc Wachter
 */
public interface StarRepository extends CrudRepository <StarEntity, Long> {
}
