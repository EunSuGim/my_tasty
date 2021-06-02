package com.counchcoding.project.domain.restaurants;

import com.counchcoding.project.domain.categories.Categories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
public class Restaurants {

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
//
    @Column(nullable = false)
    private float star_rate;
//
    @Column(nullable = false)
    private Date visit_date;
//
    @Column(nullable = true)
    private String memo;
//
//    @Column(nullable = true)
//    private Date reg_date;
//
//    @Column(nullable = true)
//    private Date mod_date;

    @Builder
    public Restaurants
            (String name, String address, Date visit_date,
             float star_rate,Categories category_id, String memo){
        this.name = name;
        this.address = address;
        this.visit_date = visit_date;
        this.star_rate = star_rate;
        this.category_id = category_id;
        this.memo = memo;
    }

}
