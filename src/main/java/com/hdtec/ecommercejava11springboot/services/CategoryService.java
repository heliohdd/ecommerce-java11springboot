package com.hdtec.ecommercejava11springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hdtec.ecommercejava11springboot.dto.CategoryDTO;
import com.hdtec.ecommercejava11springboot.model.Category;
import com.hdtec.ecommercejava11springboot.repositories.CategoryRepository;
import com.hdtec.ecommercejava11springboot.services.exceptions.DataIntegrityException;
import com.hdtec.ecommercejava11springboot.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {		//	CategoriaService

	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category find(Integer id) {
		Optional<Category> obj = categoryRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Category.class.getName()));
	}

	public Category insert(Category obj) {
		obj.setId(null);
		return categoryRepository.save(obj);
	}

	public Category update(Category obj) {
		find(obj.getId());
		return categoryRepository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoryRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Can't exclude a category that has any product.");
		}
	}

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}

	public Category fromDTO(@Valid CategoryDTO objDto) {
		return new Category(objDto.getId(), objDto.getName());
	}
}