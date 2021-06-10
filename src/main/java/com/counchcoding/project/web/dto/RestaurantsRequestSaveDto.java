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
    private LocalDate visit_date;
    private float star_rate;
    private String memo;
    private Long categoriesId;

    @Builder
    public RestaurantsRequestSaveDto
            (String name, String address, LocalDate visit_date,
             float star_rate, Long categoriesId, String memo)
    {
        this.name = name;
        this.address = address;
        this.visit_date = visit_date;
        this.star_rate = star_rate;
        this.categoriesId = categoriesId;
        this.memo = memo;
    }

    public Restaurants toEntity(Categories categories){
        return Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .category(categories)
                .memo(memo)
                .build();
    }

}
