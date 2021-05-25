package com.counchcoding.project.web.dto;


import com.counchcoding.project.domain.categories.Categories;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoriesRequestSaveDto {
    private String name;
    private String code;

    @Builder
    public CategoriesRequestSaveDto(String name, String code){
        this.name = name;
        this.code = code;
    }

    public Categories toEntity(){
        return Categories.builder()
                .name(name)
                .code(code)
                .build();
    }

}
