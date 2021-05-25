package com.counchcoding.project.web;

import com.counchcoding.project.service.categories.CategoriesService;
import com.counchcoding.project.web.dto.CategoriesRequestSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CategoriesApiController {

    private final CategoriesService categoriesService;

    @PostMapping("/api/v1/categories")
    public Long save (@RequestBody CategoriesRequestSaveDto requestDto){
        return categoriesService.save(requestDto);
    }

}
