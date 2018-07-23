package co.napsak.api.service;

import co.napsak.api.model.domain.Place;
import co.napsak.api.model.exception.NotFoundException;
import co.napsak.api.model.request.SavePlaceRequest;
import co.napsak.api.model.request.UpdatePlaceRequest;
import co.napsak.api.repository.PlaceRepository;
import co.napsak.api.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final Scheduler scheduler;

    @Autowired
    public PlaceService(PlaceRepository placeRepository,
                        @Qualifier("slowIOScheduler") Scheduler scheduler) {
        this.placeRepository = placeRepository;
        this.scheduler = scheduler;
    }

    public Flux<Place> listPlaces() {
        return Flux.fromIterable(placeRepository.findAll())
                .publishOn(scheduler);
    }

    public Mono<Place> getPlaceById(Long id) {
        return Mono.justOrEmpty(placeRepository.findById(id))
                .switchIfEmpty(Mono.error(new NotFoundException(Strings.PLACE_NOT_FOUND)))
                .publishOn(scheduler);
    }

    @Transactional
    public Mono<Place> savePlace(Mono<SavePlaceRequest> request) {
        return request
                .map(r -> placeRepository.save(convertSaveRequest(r)))
                .publishOn(scheduler);
    }

    @Transactional
    public Mono<Place> updatePlace(Mono<UpdatePlaceRequest> request) {
        return request
                .zipWhen(r -> getPlaceById(r.getId()),
                        (req, place) -> convertUpdateRequest(place, req))
                .map(placeRepository::save)
                .publishOn(scheduler);
    }

    private Place convertSaveRequest(SavePlaceRequest request) {
        return Place.builder()
                .name(request.getName())
                .address(request.getAddress())
                .latitude(request.getLat())
                .longitude(request.getLng())
                .isApproved(false)
                .build();
    }

    private Place convertUpdateRequest(Place place, UpdatePlaceRequest request) {
        place.setName(request.getName());
        place.setAddress(request.getAddress());
        place.setLatitude(request.getLat());
        place.setLongitude(request.getLng());

        return place;
    }
}
