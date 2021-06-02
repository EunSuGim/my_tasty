package com.counchcoding.project.web;


import com.counchcoding.project.service.restaurants.RestaurantsService;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestaurantsApiController {

    private final RestaurantsService restaurantsService;

    @PostMapping("/api/v1/restaurants")
    public Long save(@RequestBody RestaurantsRequestSaveDto requestSaveDto){
        return restaurantsService.save(requestSaveDto);
    }

    @DeleteMapping("/api/v1/restaurants/{id}")
    public void delete(@PathVariable Long id){
        restaurantsService.delete(id);
    }

}
