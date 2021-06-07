package com.counchcoding.project.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoriesUpdateRequestDto {

    private String name;
    private String code;

    @Builder
    public CategoriesUpdateRequestDto(String name, String code){
        this.name = name;
        this.code = code;

    }




}
