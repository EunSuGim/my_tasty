package com.counchcoding.project.service.categories;


import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.categories.CategoriesRepository;
import com.counchcoding.project.web.dto.CategoriesRequestSaveDto;
import com.counchcoding.project.web.dto.CategoriesUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public Long save (CategoriesRequestSaveDto requestDto){
        return categoriesRepository.save(requestDto.toEntity()).getId();
    }

    public List<Categories> findAll(){
        return categoriesRepository.findAll();
    }

    public Categories findById(long id){
        Categories categories =
                categoriesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));
        return categories;
    }

    @Transactional
    public Long update(Long id, CategoriesUpdateRequestDto requestDto){
        Categories categories =
                categoriesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));

        categories.update(id,requestDto);
        return id;
    }

    @Transactional
    public void delete(long id){
        Categories categories =
                categoriesRepository.findById(id)
                        .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));
        categoriesRepository.delete(categories);
    }

}
