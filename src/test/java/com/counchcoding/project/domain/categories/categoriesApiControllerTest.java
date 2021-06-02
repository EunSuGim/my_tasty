package com.counchcoding.project.domain.categories;

import com.counchcoding.project.Application;
import com.counchcoding.project.web.dto.CategoriesRequestSaveDto;
import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.categories.CategoriesRepository;
import com.counchcoding.project.web.dto.CategoriesUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class categoriesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private CategoriesRepository categoriesRepository;

//    @After
//    public void tearDown() throws Exception{
//        categoriesRepository.deleteAll();
//    }

    @Test
    public void categories_enroll_test(){
        String name = "한식";
        String code = "KOR";

        CategoriesRequestSaveDto requestDto =
                CategoriesRequestSaveDto.builder()
                .name(name)
                .code(code)
                .build();

        String url = "http://localhost:" + port + "/api/v1/categories";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Categories> categoriesList =categoriesRepository.findAll();

        assertThat(categoriesList.get(0).getName()).isEqualTo(name);
        assertThat(categoriesList.get(0).getCode()).isEqualTo(code);

    }

    @Test
    public void categories_findAll_test(){
        String name = "한식";
        String code = "KOR";

        categoriesRepository.save(Categories.builder()
                .name(name)
                .code(code)
                .build());

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories";

        ResponseEntity<Categories[]> responseEntity = testRestTemplate.getForEntity(url, Categories[].class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Categories res_categories = Arrays.asList(responseEntity.getBody()).get(0);

        assertThat(res_categories.getName()).isEqualTo(name);
        assertThat(res_categories.getCode()).isEqualTo(code);

    }

    @Test
    public void categories_findId_test(){
        String name = "한식";
        String code = "KOR";

        Categories categories =
                categoriesRepository.save(Categories.builder()
                        .code(code)
                        .name(name)
                        .build());

        Long givenDataId = categories.getId();

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories/"
                + givenDataId;

        ResponseEntity<Categories> responseEntity =
                testRestTemplate.getForEntity(url,Categories.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCode()).isEqualTo(code);
        assertThat(responseEntity.getBody().getName()).isEqualTo(name);
    }

    @Test
    public void categories_update_test(){
        String name = "한식";
        String code = "KOR";

        Categories save_categories =
                categoriesRepository.save(Categories.builder()
                        .name(name)
                        .code(code)
                        .build());

        Long updateId = save_categories.getId();

        String expectedname = "한정식";

        CategoriesUpdateRequestDto updateRequestDto =
                CategoriesUpdateRequestDto.builder()
                        .name(expectedname)
                        .build();

        String url = "http://localhost:"+port+"/api/v1/restaurants-categories/"+updateId;

        ResponseEntity<Long>  responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(updateRequestDto), Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Categories> all = categoriesRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedname);
    }

    @Test
    public void categories_delete_test(){
        String name = "한식";
        String code = "KOR";

        Long removeId = categoriesRepository.save(Categories.builder()
                .code(code)
                .name(name)
                .build()).getId();

        String url = "http://localhost:" + port + "/api/v1/restaurants-categories/"+removeId;

        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.DELETE, new HttpEntity<>(removeId), Long.class);

        List<Categories> allcategories = categoriesRepository.findAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allcategories.size()).isEqualTo(0);
    }

}
