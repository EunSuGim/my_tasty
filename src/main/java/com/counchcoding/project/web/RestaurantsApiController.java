package com.counchcoding.project.web;


import com.counchcoding.project.domain.restaurants.Restaurants;
import com.counchcoding.project.service.restaurants.RestaurantsService;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.List;

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

    @GetMapping("/api/v1/restaurants/{search}")
    public List<Restaurants> findRestaurants(@PathVariable String search){
        List<Restaurants> restaurantsList = restaurantsService.findAll();
        List<Restaurants> search_restaurantsList = null;

        for(Restaurants res : restaurantsList){
            if(res.getName().equals(search)){
                search_restaurantsList.add(res);
            }else if(res.getAddress().equals(search)){
                search_restaurantsList.add(res);
            }

        }
        return search_restaurantsList;
    }
    @GetMapping("/api/v1/restaurants")
    public List<Restaurants> findAll(){
        return restaurantsService.findAll();
    }

//    @PutMapping("/api/v1/restaurants/{id}")
//    public Long update(@PathVariable Long id, @RequestBody)

}
