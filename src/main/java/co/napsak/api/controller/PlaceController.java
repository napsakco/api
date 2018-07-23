package co.napsak.api.controller;

import co.napsak.api.model.domain.Place;
import co.napsak.api.model.request.SavePlaceRequest;
import co.napsak.api.model.request.UpdatePlaceRequest;
import co.napsak.api.service.PlaceService;
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
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;

    @Autowired
    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    Flux<Place> listPlaces() {
        return placeService.listPlaces();
    }

    @GetMapping("/{id}")
    Mono<Place> getPlaceById(@PathVariable Long id) {
        return placeService.getPlaceById(id);
    }

    @PostMapping
    Mono<Place> savePlace(@RequestBody Mono<SavePlaceRequest> request) {
        return placeService.savePlace(request);
    }

    @PutMapping
    Mono<Place> updatePlace(@RequestBody Mono<UpdatePlaceRequest> request) {
        return placeService.updatePlace(request);
    }
}
