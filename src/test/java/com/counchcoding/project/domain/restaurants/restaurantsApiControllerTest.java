package com.counchcoding.project.domain.restaurants;

import com.counchcoding.project.Application;
import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.categories.CategoriesRepository;
import com.counchcoding.project.service.categories.CategoriesService;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
import com.counchcoding.project.web.dto.RestaurantsUpdateRequestDto;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.sound.midi.Soundbank;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class restaurantsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestaurantsRepository restaurantsRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;


    @Test
    public void restaurants_enroll_test(){
        String name = "자연식당";
        String address = "주소";
        LocalDate visit_date = LocalDate.now();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        categoriesRepository.save(categories);


        Long categoriesId = categories.getId();

        RestaurantsRequestSaveDto requestSaveDto =
                RestaurantsRequestSaveDto.builder()
                        .name(name)
                        .address(address)
                        .visit_date(visit_date)
                        .star_rate(star_rate)
                        .categoriesId(categoriesId)
                        .memo(memo)
                        .build();

        String url = "http://localhost:" + port + "/api/v1/restaurants";

        ResponseEntity<Long> responseEntity =
                testRestTemplate.postForEntity(url, requestSaveDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Restaurants> restaurantsList = restaurantsRepository.findAll();

        assertThat(restaurantsList.get(0).getName()).isEqualTo(name);
        assertThat(restaurantsList.get(0).getStar_rate()).isEqualTo(star_rate);

    }

    @Test
    public void restaurants_delete_test(){
        String name = "자연식당";
        String address = "주소";
        LocalDate visit_date = LocalDate.now();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        Long removeId = restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .memo(memo)
                .category(categories)
                .build()).getId();

        String url = "http://localhost:" + port
                + "/api/v1/restaurants/"+removeId;

        ResponseEntity<Long> responseEntity = testRestTemplate
                .exchange(url, HttpMethod.DELETE, new HttpEntity<>(removeId), Long.class);

        List<Restaurants> allRestaurants = restaurantsRepository.findAll();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(allRestaurants.size()).isEqualTo(0);

    }

    @Test
    public void restaurants_Search_test(){
        String name = "store";
        String address = "suwon";
        LocalDate visit_date = LocalDate.now();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .memo(memo)
                .category(categories)
                .build());


        String url = "http://localhost:" + port
                + "/api/v1/restaurants/";

        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();

        parameters.add("name",name);
//        parameters.add("address",address);


        ResponseEntity<Restaurants[]> responseEntity =
                testRestTemplate.getForEntity(url,Restaurants[].class,parameters);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Restaurants restaurants = Arrays.asList(responseEntity.getBody()).get(0);

        assertThat(restaurants.getName()).isEqualTo(name);
        assertThat(restaurants.getAddress()).isEqualTo(address);
    }

    @Test
    public void restaurants_findAll_test(){
        String name = "store";
        String address = "suwon";
        LocalDate visit_date = LocalDate.now();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .memo(memo)
                .category(categories)
                .build());



        String url = "http://localhost:" + port
                + "/api/v1/restaurants";

        ResponseEntity<Restaurants[]> responseEntity =
                testRestTemplate.getForEntity(url,Restaurants[].class);


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Restaurants restaurants = Arrays.asList(responseEntity.getBody()).get(0);

        assertThat(restaurants.getName()).isEqualTo(name);

    }

    @Test
    public void restaurants_findId_test(){
        String name = "store";
        String address = "suwon";
        LocalDate visit_date = LocalDate.now();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        Restaurants restaurants =
                restaurantsRepository.save(Restaurants.builder()
                        .name(name)
                        .address(address)
                        .visit_date(visit_date)
                        .star_rate(star_rate)
                        .memo(memo)
                        .category(categories)
                        .build());

        Long givenDataId = restaurants.getId();

        String url = "http://localhost:" + port + "/api/v1/restaurants/" +
                givenDataId;

        ResponseEntity<Restaurants> responseEntity =
                testRestTemplate.getForEntity(url, Restaurants.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getName()).isEqualTo(name);
        assertThat(responseEntity.getBody().getStar_rate()).isEqualTo(star_rate);

    }


    @Test
    public void restaurants_update_test(){
        //기본 Restaurants 컬럼 선언
        String name = "store";
        String address = "suwon";
        LocalDate visit_date = LocalDate.now();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        Restaurants restaurants =
                restaurantsRepository.save(Restaurants.builder()
                        .name(name)
                        .address(address)
                        .visit_date(visit_date)
                        .star_rate(star_rate)
                        .memo(memo)
                        .category(categories)
                        .build());

        Long updateId = restaurants.getId();

        String expectedName = "내가게";

        RestaurantsUpdateRequestDto updateRequestDto =
                RestaurantsUpdateRequestDto.builder()
                        .name(expectedName)
                        .build();

        String url = "http://localhost:" + port + "/api/v1/restaurants/" + updateId;

        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, new HttpEntity<>(updateRequestDto), Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Restaurants> all = restaurantsRepository.findAll();
        assertThat(all.get(0).getName()).isEqualTo(expectedName);

    }

}
