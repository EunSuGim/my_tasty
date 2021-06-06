package com.counchcoding.project.domain.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long> {

    //ToDo: 가게이름 및 주소 검색 JPA 메소드
    List<Restaurants> findByNameOrAddress(String name, String address);
}
