package com.hdtec.ecommercejava11springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.hdtec.ecommercejava11springboot.model.Category;

public class CategoryDTO implements Serializable {		//	CategoriaDTO
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Name is mandatory")
	@Length(min=5, max=80, message="Length needs to be between 5 and 80 characters")
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Category obj) {
		id = obj.getId();
		name = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}