package com.hdtec.ecommercejava11springboot.dto;

import java.io.Serializable;

import com.hdtec.ecommercejava11springboot.model.Product;

public class ProductDTO implements Serializable {		//	Produto
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Double price;

	public ProductDTO() {
	}

	public ProductDTO(Product obj) {
		id = obj.getId();
		name = obj.getName();
		price = obj.getPrice();
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}