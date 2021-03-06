package com.hdtec.ecommercejava11springboot.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hdtec.ecommercejava11springboot.dto.CategoryDTO;
import com.hdtec.ecommercejava11springboot.model.Category;
import com.hdtec.ecommercejava11springboot.services.CategoryService;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {		// CategoriaResource

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> find(@PathVariable Integer id) {

		Category obj = categoryService.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO objDto) {
		Category obj = categoryService.fromDTO(objDto);
		obj = categoryService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoryDTO objDto, @PathVariable Integer id) {
		Category obj = categoryService.fromDTO(objDto);
		obj.setId(id);
		obj = categoryService.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoryService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List<Category> list = categoryService.findAll();
		List<CategoryDTO> listDto = list.stream().map(obj -> new CategoryDTO(obj)).collect(Collectors.toList());  
		return ResponseEntity.ok().body(listDto);
	}
	
//	@RequestMapping(value="categories/page/0/24/name/ASC") -> @PathVariable approach 
//	@RequestMapping(value="categories/page?page=0&linesPerPage=24&orderBy=name&direction=ASC") -> @RequestParam approach
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Category> list = categoryService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoryDTO> listDto = list.map(obj -> new CategoryDTO(obj));  
		return ResponseEntity.ok().body(listDto);
	}
}