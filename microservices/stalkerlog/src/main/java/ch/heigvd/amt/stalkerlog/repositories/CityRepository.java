package ch.heigvd.amt.stalkerlog.repositories;

import ch.heigvd.amt.stalkerlog.entities.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Spring data repository to generate operations on the city resource
 *
 * @author Alison Savary, Luc Wachter
 */
public interface CityRepository extends PagingAndSortingRepository<CityEntity, Long> {
    Page<CityEntity> findAllByOrderByName(Pageable pageable);
}
