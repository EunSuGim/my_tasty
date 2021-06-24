package com.counchcoding.project.web.dto;

import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.restaurants.Restaurants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@NoArgsConstructor
public class RestaurantsRequestSaveDto {
    private String name;
    private String address;
    private LocalDate visitDate;
    private float starRate;
    private String memo;
    private Long categoriesId;

    @Builder
    public RestaurantsRequestSaveDto
            (String name, String address, LocalDate visitDate,
             float starRate, Long categoriesId, String memo)
    {
        this.name = name;
        this.address = address;
        this.visitDate = visitDate;
        this.starRate = starRate;
        this.categoriesId = categoriesId;
        this.memo = memo;
    }

    public Restaurants toEntity(Categories categoriesId){
        return Restaurants.builder()
                .name(name)
                .address(address)
                .visitDate(visitDate)
                .starRate(starRate)
                .categoriesId(categoriesId)
                .memo(memo)
                .build();
    }

}
