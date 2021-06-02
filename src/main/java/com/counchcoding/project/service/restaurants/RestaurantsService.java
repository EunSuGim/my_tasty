package com.counchcoding.project.service.restaurants;

import com.counchcoding.project.domain.restaurants.Restaurants;
import com.counchcoding.project.domain.restaurants.RestaurantsRepository;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RestaurantsService {
    private final RestaurantsRepository restaurantsRepository;

    public Long save (RestaurantsRequestSaveDto requestSaveDto){
        return restaurantsRepository.save(requestSaveDto.toEntity()).getId();
    }

    @Transactional
    public void delete(long id){
        Restaurants restaurants =
                restaurantsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 가게가 없습니다."));
        restaurantsRepository.delete(restaurants);
    }

    public List<Restaurants> findAll(){
        return restaurantsRepository.findAll();

    }





}
