package com.counchcoding.project.domain.restaurants;

import com.counchcoding.project.Application;
import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.web.dto.RestaurantsRequestSaveDto;
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

import javax.sound.midi.Soundbank;
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


    @Test
    public void restaurants_enroll_test(){
        String name = "자연식당";
        String address = "주소";
        Date visit_date = new Date();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories_id = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        RestaurantsRequestSaveDto requestSaveDto =
                RestaurantsRequestSaveDto.builder()
                        .name(name)
                        .address(address)
                        .visit_date(visit_date)
                        .star_rate(star_rate)
                        .categories_id(categories_id)
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
        Date visit_date = new Date();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories_id = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        Long removeId = restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .memo(memo)
                .category_id(categories_id)
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
    public void restaurants_findSearch_test(){
        String name = "store";
        String address = "suwon";
        Date visit_date = new Date();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories_id = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .memo(memo)
                .category_id(categories_id)
                .build());


        String url = "http://localhost:" + port
                + "/api/v1/restaurants/"+name;


        ResponseEntity<Restaurants[]> responseEntity =
                testRestTemplate.getForEntity(url,Restaurants[].class);



//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody().getName()).isEqualTo(name);
//        assertThat(responseEntity.getBody().getAddress()).isEqualTo(address);
    }

    @Test
    public void restaurants_findAll_test(){
        String name = "store";
        String address = "suwon";
        Date visit_date = new Date();
        float star_rate = 3.5f;
        String memo = null;
        Categories categories_id = Categories.builder()
                .name("한식")
                .code("KOR")
                .build();

        restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visit_date(visit_date)
                .star_rate(star_rate)
                .memo(memo)
                .category_id(categories_id)
                .build());


        String url = "http://localhost:" + port
                + "/api/v1/restaurants";

        ResponseEntity<Restaurants[]> responseEntity =
                testRestTemplate.getForEntity(url,Restaurants[].class);


        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Restaurants restaurants = Arrays.asList(responseEntity.getBody()).get(0);

        assertThat(restaurants.getName()).isEqualTo(name);

    }



}
