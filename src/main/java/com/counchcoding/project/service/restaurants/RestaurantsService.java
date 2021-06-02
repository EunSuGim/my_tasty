package com.counchcoding.project.service.restaurants;

import com.counchcoding.project.domain.restaurants.Restaurants;
import com.counchcoding.project.domain.restaurants.RestaurantsRepository;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RestaurantsService {
    private final RestaurantsRepository restaurantsRepository;

    public Long save (RestaurantsRequestSaveDto requestSaveDto){
        return restaurantsRepository.save(requestSaveDto.toEntity()).getId();
    }

}
