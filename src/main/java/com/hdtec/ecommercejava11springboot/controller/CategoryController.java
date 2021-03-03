package com.hdtec.ecommercejava11springboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hdtec.ecommercejava11springboot.model.Category;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Category> list() {

		Category cat1 = new Category(1, "computing");
		Category cat2 = new Category(2, "office");
		
		List<Category> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		
		return list;
	}
}