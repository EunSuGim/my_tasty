package com.counchcoding.project.service.restaurants;

import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.categories.CategoriesRepository;
import com.counchcoding.project.domain.restaurants.Restaurants;
import com.counchcoding.project.domain.restaurants.RestaurantsRepository;
import com.counchcoding.project.service.categories.CategoriesService;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import com.counchcoding.project.web.dto.RestaurantsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class RestaurantsService {
    private final RestaurantsRepository restaurantsRepository;
    private final CategoriesService categoriesService;

    public Long save (RestaurantsRequestSaveDto requestSaveDto){
        Categories categories = categoriesService.findById(requestSaveDto.getCategoryId());
        return restaurantsRepository.save(requestSaveDto.toEntity(categories)).getId();
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(long id){
        Restaurants restaurants =
                restaurantsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 가게가 없습니다."));
        restaurantsRepository.delete(restaurants);
    }

    /**
     *
     * @param name
     * @param address
     * @return
     */
    public List<Restaurants> getList(String name, String address){

        if(!Objects.isNull(name)){
            return restaurantsRepository.findByNameContaining(name);

        }else if(!Objects.isNull(address)){
            return restaurantsRepository.findByAddressContaining(address);
        }

        return restaurantsRepository.findAll();

    }

    /**
     *
     * @param id
     * @return
     */
    public Restaurants findById(long id){
        Restaurants restaurants =
                restaurantsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 가게가 없습니다."));
        return restaurants;
    }

    /**
     *
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public Long update(Long id, RestaurantsUpdateRequestDto requestDto){
        Restaurants restaurants =
                restaurantsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 가게 정보가 없습니다."));



        restaurants.update(requestDto,restaurants.getCategory());
        return id;

    }







}
