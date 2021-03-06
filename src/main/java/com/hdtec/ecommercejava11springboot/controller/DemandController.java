package com.hdtec.ecommercejava11springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdtec.ecommercejava11springboot.model.Demand;
import com.hdtec.ecommercejava11springboot.services.DemandService;

@RestController
@RequestMapping(value="/demands")
public class DemandController {		//	PedidoResource
	
	@Autowired
	private DemandService demandService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Demand> find(@PathVariable Integer id) {
		Demand obj = demandService.find(id);
		return ResponseEntity.ok().body(obj);
	}
}