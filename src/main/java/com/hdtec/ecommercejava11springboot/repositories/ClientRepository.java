package com.hdtec.ecommercejava11springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdtec.ecommercejava11springboot.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}