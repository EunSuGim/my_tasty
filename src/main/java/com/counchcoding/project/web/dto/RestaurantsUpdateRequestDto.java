package com.counchcoding.project.web.dto;

import com.counchcoding.project.domain.categories.Categories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RestaurantsUpdateRequestDto {

    String name;
    String address;
    LocalDate visitDate;
    float starRate;
    Long categoryId;
    String memo;

    @Builder
    public RestaurantsUpdateRequestDto(
            String name, String address, LocalDate visitDate,
            float starRate, Long categoryId, String memo){
        this.name = name;
        this.address = address;
        this.visitDate = visitDate;
        this.starRate = starRate;
        this.categoryId = categoryId;
        this.memo = memo;
    }

}
