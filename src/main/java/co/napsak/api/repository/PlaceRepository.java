package co.napsak.api.repository;

import co.napsak.api.model.domain.Place;
import org.springframework.data.repository.CrudRepository;

public interface PlaceRepository extends CrudRepository<Place, Long> {
}
