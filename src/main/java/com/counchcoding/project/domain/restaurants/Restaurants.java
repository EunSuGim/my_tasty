package com.counchcoding.project.domain.restaurants;

import com.counchcoding.project.domain.BaseTimeEntity;
import com.counchcoding.project.domain.categories.Categories;
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
    @JoinColumn(name = "categories_id")
    private Categories category_id;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private float star_rate;

    //ToDo: 날짜만 표기여서 LocalDate
    @Column(nullable = false)
    private LocalDate visit_date;

    @Column(nullable = true)
    private String memo;


    @Builder
    public Restaurants
            (String name, String address, LocalDate visit_date,
             float star_rate,Categories category_id, String memo){
        this.name = name;
        this.address = address;
        this.visit_date = visit_date;
        this.star_rate = star_rate;
        this.category_id = category_id;
        this.memo = memo;
    }

}
