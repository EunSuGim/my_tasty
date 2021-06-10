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
    String name;
    String address;
    LocalDate visit_date;
    float star_rate;
    Long categoriesId;
    String memo;

    @Builder
    public RestaurantsUpdateRequestDto(
            String name, String address, LocalDate visit_date,
            float star_rate, Long categoriesId, String memo){
        this.name = name;
        this.address = address;
        this.visit_date = visit_date;
        this.star_rate = star_rate;
        this.categoriesId = categoriesId;
        this.memo = memo;
    }

}
