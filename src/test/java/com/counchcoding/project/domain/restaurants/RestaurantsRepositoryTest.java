package com.counchcoding.project.domain.restaurants;

import com.counchcoding.project.Application;
import com.counchcoding.project.domain.categories.Categories;
import com.counchcoding.project.domain.categories.CategoriesRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestaurantsRepositoryTest {

    @Autowired
    RestaurantsRepository restaurantsRepository;

    @After
    public void cleanUp(){ restaurantsRepository.deleteAll();}

    @Test
    public void load_restaurants(){
        String name = "자연식당";
        String address = "주소";
        LocalDate visitDate = LocalDate.now();
        float starRate = 2.5F;


        restaurantsRepository.save(Restaurants.builder()
                .name(name)
                .address(address)
                .visitDate(visitDate)
                .starRate(starRate)
                .build());

        List<Restaurants> restaurantsList = restaurantsRepository.findAll();

        Restaurants restaurants = restaurantsList.get(0);

        assertThat(restaurants.getName()).isEqualTo(name);
        assertThat(restaurants.getAddress()).isEqualTo(address);
        assertThat(restaurants.getStarRate()).isEqualTo(starRate);


    }
}
