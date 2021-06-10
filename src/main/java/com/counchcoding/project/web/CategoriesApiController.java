package com.counchcoding.project.web;

import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.service.categories.CategoriesService;
import com.counchcoding.project.web.dto.CategoriesRequestSaveDto;
import com.counchcoding.project.web.dto.CategoriesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CategoriesApiController {

    private final CategoriesService categoriesService;

    @PostMapping("/api/v1/restaurants-categories")
    public Long save (@RequestBody CategoriesRequestSaveDto requestDto){
        return categoriesService.save(requestDto);
    }

    @GetMapping("/api/v1/restaurants-categories")
    public List<Categories> findAll(){
        return categoriesService.findAll();
    }

    @GetMapping("/api/v1/restaurants-categories/{id}")
    public Categories findById(@PathVariable Long id){
        return categoriesService.findById(id);
    }

    @PutMapping("/api/v1/restaurants-categories/{id}")
    public Long update(@PathVariable Long id, @RequestBody CategoriesUpdateRequestDto requestDto){
        return categoriesService.update(id,requestDto);
    }

    @DeleteMapping("/api/v1/restaurants-categories/{id}")
    public void delete(@PathVariable Long id){
        categoriesService.delete(id);
    }


}
