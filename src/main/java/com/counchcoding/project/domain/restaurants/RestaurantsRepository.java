package com.counchcoding.project.domain.restaurants;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantsRepository extends JpaRepository<Restaurants, Long> {

    List<Restaurants> findByNameContaining(String name);
    List<Restaurants> findByAddressContaining(String address);
}
