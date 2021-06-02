package com.counchcoding.project.domain.categories;

import com.counchcoding.project.Application;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CategoriesRepositoryTest {

    @Autowired
    CategoriesRepository categoriesRepository;

//    @After
//    public void cleanUp(){
//        categoriesRepository.deleteAll();
//    }

    @Test
    public void load_categories(){
        String name = "한식";
        String code = "KOR";

        categoriesRepository.save(Categories.builder()
                .code(code)
                .name(name)
                .build());

        List<Categories> categoriesList = categoriesRepository.findAll();

        Categories categories = categoriesList.get(0);

        assertThat(categories.getCode(), is(equalTo(code)));
        assertThat(categories.getName(), is(equalTo(name)));




    }
}
