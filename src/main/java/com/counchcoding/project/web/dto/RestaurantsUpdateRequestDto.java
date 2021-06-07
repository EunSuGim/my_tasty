package com.counchcoding.project.web.dto;

import com.counchcoding.project.domain.categories.Categories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RestaurantsUpdateRequestDto {

    //ToDo: 업데이트할 Restaurants 컬럼 선언
    String name = "store";
    String address = "suwon";
    LocalDate visit_date = LocalDate.now();
    float star_rate = 3.5f;
    String memo = null;

    @Builder
    public RestaurantsUpdateRequestDto(String name){
        this.name = name;
    }

}
