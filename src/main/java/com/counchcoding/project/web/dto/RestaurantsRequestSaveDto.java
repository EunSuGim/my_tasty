package com.counchcoding.project.web.dto;

import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.restaurants.Restaurants;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class RestaurantsRequestSaveDto {
    private String name;
    private String address;
    private Date visit_date;
    private float star_rate;
    private String memo;
    private Categories categories_id;

    @Builder
    public RestaurantsRequestSaveDto
            (String name, String address, Date visit_date,
             float star_rate, Categories categories_id, String memo)
    {
        this.name = name;
        this.address = address;
        this.visit_date = visit_date;
        this.star_rate = star_rate;
        this.categories_id = categories_id;
        this.memo = memo;
    }

    public Restaurants toEntity(){
        return Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .category_id(categories_id)
                .memo(memo)
                .build();
    }

}
