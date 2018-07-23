package co.napsak.api.service;

import co.napsak.api.model.domain.Event;
import co.napsak.api.model.domain.Place;
import co.napsak.api.model.exception.NotFoundException;
import co.napsak.api.model.request.SaveEventRequest;
import co.napsak.api.model.request.UpdateEventRequest;
import co.napsak.api.repository.EventRepository;
import co.napsak.api.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

import java.util.Date;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final Scheduler scheduler;

    @Autowired
    public EventService(EventRepository eventRepository,
                        @Qualifier("slowIOScheduler") Scheduler scheduler) {
        this.eventRepository = eventRepository;
        this.scheduler = scheduler;
    }

    public Flux<Event> listEvents() {
        return Flux.fromIterable(eventRepository.findAll())
                .switchIfEmpty(Mono.error(new NotFoundException(Strings.EVENT_NOT_FOUND)))
                .publishOn(scheduler);
    }

    public Mono<Event> getEventById(Long id) {
        return Mono.justOrEmpty(eventRepository.findById(id))
                .switchIfEmpty(Mono.error(new NotFoundException(Strings.EVENT_NOT_FOUND)))
                .publishOn(scheduler);
    }

    @Transactional
    public Mono<Event> saveEvent(Mono<SaveEventRequest> request) {
        return request
                .map(r -> eventRepository.save(convertSaveRequest(r)))
                .publishOn(scheduler);
    }

    @Transactional
    public Mono<Event> updateEvent(Mono<UpdateEventRequest> request) {
        return request
                .zipWhen(r -> getEventById(r.getId()),
                        (req, event) -> convertUpdateRequest(event, req))
                .map(eventRepository::save)
                .publishOn(scheduler);
    }

    private Event convertSaveRequest(SaveEventRequest request) {
        return Event.builder()
                .name(request.getName())
                .description(request.getDescription())
                .creationDate(new Date().getTime())
                .place(Place.builder().id(request.getPlaceId()).build())
                .approved(false)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    private Event convertUpdateRequest(Event e, UpdateEventRequest request) {
        e.setName(request.getName());
        e.setDescription(request.getDescription());
        e.setPlace(Place.builder().id(request.getPlaceId()).build());
        e.setStartDate(request.getStartDate());
        e.setEndDate(request.getEndDate());

        return e;
    }

}
