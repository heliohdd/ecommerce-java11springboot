package com.hdtec.ecommercejava11springboot.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdtec.ecommercejava11springboot.model.Demand;
import com.hdtec.ecommercejava11springboot.repositories.DemandRepository;
import com.hdtec.ecommercejava11springboot.services.exceptions.ObjectNotFoundException;

@Service
public class DemandService {		//	PedidoService
	
	@Autowired
	private DemandRepository demandRepository;

	public Demand find(Integer id) {
		Optional<Demand> obj = demandRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Demand.class.getName()));
	}
}