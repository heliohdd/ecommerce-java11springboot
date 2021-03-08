package com.hdtec.ecommercejava11springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hdtec.ecommercejava11springboot.controller.utils.URL;
import com.hdtec.ecommercejava11springboot.dto.ProductDTO;
import com.hdtec.ecommercejava11springboot.model.Product;
import com.hdtec.ecommercejava11springboot.services.ProductService;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Product> find(@PathVariable Integer id) {
		Product obj = productService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value="name", defaultValue="") String name, 
			@RequestParam(value="categories", defaultValue="") String categories, 
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		String nameDecoded = URL.decodeParam(name);
		List<Integer> ids = URL.decodeIntList(categories);
		Page<Product> list = productService.search(nameDecoded, ids, page, linesPerPage, orderBy, direction);
		Page<ProductDTO> listDto = list.map(obj -> new ProductDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}