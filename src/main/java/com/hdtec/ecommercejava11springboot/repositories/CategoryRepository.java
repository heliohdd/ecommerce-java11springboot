package com.hdtec.ecommercejava11springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdtec.ecommercejava11springboot.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}