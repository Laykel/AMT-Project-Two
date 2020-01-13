package ch.heigvd.amt.citylog.repositories;

import ch.heigvd.amt.citylog.entities.CountryEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Olivier Liechti on 26/07/17.
 */
public interface CountryRepository extends CrudRepository<CountryEntity, Long> {
}
