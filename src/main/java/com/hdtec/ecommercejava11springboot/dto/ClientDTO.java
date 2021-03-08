package com.hdtec.ecommercejava11springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.hdtec.ecommercejava11springboot.model.Client;
import com.hdtec.ecommercejava11springboot.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable {		//	ClientDTO
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Name is mandatory.")
	@Length(min=5, max=120, message="Length needs to be between 5 and 120 characters.")
	private String name;
	
	@NotEmpty(message = "Email is mandatory")
	@Email(message="Invalid email.")
	private String email;

	public ClientDTO() {
	}

	public ClientDTO(Client obj) {
		id = obj.getId();
		name = obj.getName();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}