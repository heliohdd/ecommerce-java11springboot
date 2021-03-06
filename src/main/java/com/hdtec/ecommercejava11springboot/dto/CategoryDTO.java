package com.hdtec.ecommercejava11springboot.dto;

import java.io.Serializable;

import com.hdtec.ecommercejava11springboot.model.Category;

public class CategoryDTO implements Serializable {		//	CategoriaDTO
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CategoryDTO() {
	}

	public CategoryDTO(Category obj) {
		id = obj.getId();
		nome = obj.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
}