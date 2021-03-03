package com.hdtec.ecommercejava11springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdtec.ecommercejava11springboot.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}