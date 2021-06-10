package com.counchcoding.project.domain.restaurants;

import com.counchcoding.project.domain.BaseTimeEntity;
import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.categories.CategoriesRepository;
import com.counchcoding.project.web.dto.RestaurantsUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Restaurants extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categories")
    private Categories category;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Float star_rate;

    @Column(nullable = false)
    private LocalDate visit_date;

    @Column(nullable = true, length = 500)
    private String memo;


    @Builder
    public Restaurants
            (String name, String address, LocalDate visit_date,
             Float star_rate,Categories category, String memo){
        this.name = name;
        this.address = address;
        this.visit_date = visit_date;
        this.star_rate = star_rate;
        this.category = category;
        this.memo = memo;
    }

    public void update(RestaurantsUpdateRequestDto requestDto, Categories categories){
        this.name = requestDto.getName();
        this.address = requestDto.getAddress();
        this.visit_date = requestDto.getVisit_date();
        this.star_rate = requestDto.getStar_rate();
        this.category = categories;
        this.memo = requestDto.getMemo();
    }

}
