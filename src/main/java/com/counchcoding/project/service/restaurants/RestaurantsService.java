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
    private final CategoriesRepository categoriesRepository;
    /**
     * 등록 메소드
     * @param requestSaveDto 등록할 내용
     * @return
     */
    public Long save (RestaurantsRequestSaveDto requestSaveDto){

        System.out.println("@@@@@@@@@@@"+requestSaveDto.toString());
        System.out.println("@@@@@@@@@@@@@@"+requestSaveDto.getCategoriesId());
        Categories categories = categoriesService.findById(requestSaveDto.getCategoriesId());
        return restaurantsRepository.save(requestSaveDto.toEntity(categories)).getId();
    }

    /**
     * 삭제 메소드
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
     * 검색 메소드
     * @param name 식당이름
     * @param address 주소
     * @return 조건에 맞는 식당정보
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
     * 식당 ID값 검색메소드
     * @param id
     * @return
     */
    public Restaurants findById(long id){
        Restaurants restaurants =
                restaurantsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 가게가 없습니다."));
        return restaurants;
    }

    /**
     * 업데이트 메소드
     * @param id
     * @param requestDto 수정할 내용
     * @return
     */
    @Transactional
    public Long update(Long id, RestaurantsUpdateRequestDto requestDto){
        Restaurants restaurants =
                restaurantsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 가게 정보가 없습니다."));

        restaurants.update(requestDto,restaurants.getCategoriesId());
        return id;

    }







}
