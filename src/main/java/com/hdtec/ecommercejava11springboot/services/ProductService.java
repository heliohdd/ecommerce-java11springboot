package com.hdtec.ecommercejava11springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hdtec.ecommercejava11springboot.model.Category;
import com.hdtec.ecommercejava11springboot.model.Product;
import com.hdtec.ecommercejava11springboot.repositories.CategoryRepository;
import com.hdtec.ecommercejava11springboot.repositories.ProductRepository;
import com.hdtec.ecommercejava11springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Product find(Integer id) {
		Optional<Product> obj = productRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Product.class.getName()));
	}

	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);
		return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}