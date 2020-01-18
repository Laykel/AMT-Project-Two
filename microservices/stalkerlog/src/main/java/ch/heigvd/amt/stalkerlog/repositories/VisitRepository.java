package ch.heigvd.amt.stalkerlog.repositories;

import ch.heigvd.amt.stalkerlog.entities.VisitEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository to generate operations on the visit resource
 *
 * @author Alison Savary, Luc Wachter
 */
public interface VisitRepository extends CrudRepository<VisitEntity, Long> {
}
