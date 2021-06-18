package com.counchcoding.project.domain.categories;

import com.counchcoding.project.web.dto.CategoriesUpdateRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoriesId")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Builder
    public Categories(String name, String code){
        this.name = name;
        this.code = code;
    }

    public void update(Long id, CategoriesUpdateRequestDto requestDto){

        this.name = requestDto.getName();

    }


}
