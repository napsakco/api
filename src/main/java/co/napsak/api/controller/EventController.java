package co.napsak.api.controller;

import co.napsak.api.model.domain.Event;
import co.napsak.api.model.request.SaveEventRequest;
import co.napsak.api.model.request.UpdateEventRequest;
import co.napsak.api.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    Flux<Event> listEvents() {
        return eventService.listEvents();
    }

    @GetMapping("/{id}")
    Mono<Event> getEventById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping
    Mono<Event> saveEvent(@RequestBody Mono<SaveEventRequest> request) {
        return eventService.saveEvent(request);
    }

    @PutMapping
    Mono<Event> updateEvent(@RequestBody Mono<UpdateEventRequest> request) {
        return eventService.updateEvent(request);
    }
}
