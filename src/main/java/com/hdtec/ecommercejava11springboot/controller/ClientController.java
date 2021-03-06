package com.hdtec.ecommercejava11springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdtec.ecommercejava11springboot.model.Client;
import com.hdtec.ecommercejava11springboot.services.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {		//	ClienteResource

	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> find(@PathVariable Integer id) {
		Client obj = clientService.find(id);
		return ResponseEntity.ok().body(obj);
	}
}