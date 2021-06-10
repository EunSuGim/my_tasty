package com.counchcoding.project.web;


import com.counchcoding.project.domain.restaurants.Restaurants;
import com.counchcoding.project.service.restaurants.RestaurantsService;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import com.counchcoding.project.web.dto.RestaurantsUpdateRequestDto;
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

    /**
     *
     * @param requestSaveDto
     * @return
     */
    @PostMapping("/api/v1/restaurants")
    public Long save(@RequestBody RestaurantsRequestSaveDto requestSaveDto){
        return restaurantsService.save(requestSaveDto);
    }

    /**
     *
     * @param id
     */
    @DeleteMapping("/api/v1/restaurants/{id}")
    public void delete(@PathVariable Long id){
        restaurantsService.delete(id);
    }


    /**
     *
     * @param name
     * @param address
     * @return
     */
    @GetMapping("/api/v1/restaurants")
    public List<Restaurants> findByNameOrAddress(
            @RequestParam(value="name", required = false) String name,
            @RequestParam(value = "address", required = false) String address)
    {
        return restaurantsService.getList(name, address);
    }


    /**
     *
     * @paramVariable 검색할 restaurants id
     * @return restaurantsService.findById()
     */
    @GetMapping("/api/v1/restaurants/{id}")
    public Restaurants findById(@PathVariable Long id){

        return restaurantsService.findById(id);
    }


    /**
     *
     * @paramVariable 수정할 restaurants id
     * @RequestBody 수정될 데이터
     * @return restaurantsService.update()
     */
    @PutMapping("/api/v1/restaurants/{id}")
    public Long update(@PathVariable Long id, @RequestBody RestaurantsUpdateRequestDto requestDto){
        return restaurantsService.update(id,requestDto);
    }

}
