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

    //ToDo: 가게이름 및 주소 검색
    @GetMapping("/api/v1/restaurants/")
    public List<Restaurants> findByNameOrAddress(
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value = "address", required = false) String address)
    {
        return restaurantsService.findByNameOrAddress(name, address);
    }

    @GetMapping("/api/v1/restaurants")
    public List<Restaurants> findAll(){
        return restaurantsService.findAll();
    }

    @GetMapping("/api/v1/restaurants/{id}")
    public Restaurants findById(@PathVariable Long id){
        return restaurantsService.findById(id);
    }

//    @PutMapping("/api/v1/restaurants/{id}")
//    public Long update(@PathVariable Long id, @RequestBody)

}
