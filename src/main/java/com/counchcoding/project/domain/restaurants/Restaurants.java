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
@Table(name = "restaurants")
public class Restaurants extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id", insertable = false, updatable = false, nullable = false)
    private Categories category;

    public Long getCategories(){
        return category.getId();
    }

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "star_rate", nullable = false)
    private Float starRate;

    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "memo", nullable = true, length = 500)
    private String memo;


    @Builder
    public Restaurants
            (String name, String address, LocalDate visitDate,
             Float starRate,Categories category, String memo){
        this.name = name;
        this.address = address;
        this.visitDate = visitDate;
        this.starRate = starRate;
        this.category = category;
        this.memo = memo;
    }

    public void update(RestaurantsUpdateRequestDto requestDto, Categories categories){
        this.name = requestDto.getName();
        this.address = requestDto.getAddress();
        this.visitDate = requestDto.getVisitDate();
        this.starRate = requestDto.getStarRate();
        this.category = categories;
        this.memo = requestDto.getMemo();
    }

}
