package com.counchcoding.project.service.categories;


import com.counchcoding.project.domain.categories.CategoriesRepository;
import com.counchcoding.project.web.dto.CategoriesRequestSaveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;

    public Long save (CategoriesRequestSaveDto requestDto){
        return categoriesRepository.save(requestDto.toEntity()).getId();
    }

}
