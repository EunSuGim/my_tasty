package com.counchcoding.project.domain.categories;

import com.counchcoding.project.Application;
import com.counchcoding.project.web.dto.CategoriesRequestSaveDto;
import com.counchcoding.project.domain.categories.Categories;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

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

    @After
    public void tearDown() throws Exception{
        categoriesRepository.deleteAll();
    }

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

        assertThat(responseEntity.getStatusCode(),is(equalTo(HttpStatus.OK)));
        assertThat(responseEntity.getBody(), is(equalTo(1L)));

        List<Categories> categoriesList =categoriesRepository.findAll();
        assertThat(categoriesList.get(0).getName(),is(equalTo(name)));
        assertThat(categoriesList.get(0).getName(),is(equalTo(code)));

    }
}
