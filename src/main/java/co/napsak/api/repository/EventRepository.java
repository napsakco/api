package co.napsak.api.repository;

import co.napsak.api.model.domain.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {
}
