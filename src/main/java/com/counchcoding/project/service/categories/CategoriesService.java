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

    /**
     * 카테고리 등록 메소드
     * @param requestDto 등록할 정보
     * @return
     */
    public Long save (CategoriesRequestSaveDto requestDto){
        return categoriesRepository.save(requestDto.toEntity()).getId();
    }

    /**
     * 전체 검색 메소드
     * @return
     */
    public List<Categories> findAll(){
        return categoriesRepository.findAll();
    }

    /**
     * ID값으로 검색 메소드
     * @param id
     * @return
     */
    public Categories findById(long id){
        Categories categories =
                categoriesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));
        return categories;
    }

    /**
     * 업데이트 메소드
     * @param id
     * @param requestDto 수정할 정보
     * @return
     */
    @Transactional
    public Long update(Long id, CategoriesUpdateRequestDto requestDto){
        Categories categories =
                categoriesRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));

        categories.update(id,requestDto);
        return id;
    }

    /**
     * 삭제 메소드
     * @param id
     */
    @Transactional
    public void delete(long id){
        Categories categories =
                categoriesRepository.findById(id)
                        .orElseThrow(()-> new IllegalArgumentException("해당 카테고리 정보가 없습니다."));
        categoriesRepository.delete(categories);
    }

}
