package com.counchcoding.project.web;


import com.counchcoding.project.service.restaurants.RestaurantsService;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class RestaurantsApiController {

    private final RestaurantsService restaurantsService;

    @PostMapping("/api/v1/restaurants")
    public Long save(@RequestBody RestaurantsRequestSaveDto requestSaveDto){
        return restaurantsService.save(requestSaveDto);
    }

}
