package com.hdtec.ecommercejava11springboot.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.hdtec.ecommercejava11springboot.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {		//	ClienteNewDTO
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Name is mandatory.")
	@Length(min=5, max=120, message="Length needs to be between 5 and 120 characters.")
	private String name;

	@NotEmpty(message = "Email is mandatory")
	@Email(message="Invalid email.")
	private String email;
	
	@NotEmpty(message = "This field is mandatory")
	private String cpfOuCnpj;
	private Integer type;

	@NotEmpty(message = "This field is mandatory")
	private String public_Place;
	
	@NotEmpty(message = "This field is mandatory")
	private String number;
	private String complement;
	private String district;
	
	@NotEmpty(message = "This field is mandatory")
	private String zip_code;

	@NotEmpty(message = "This field is mandatory")
	private String phone1;
	private String phone2;
	private String phone3;

	private Integer cityId;

	public ClientNewDTO() {
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPublic_Place() {
		return public_Place;
	}

	public void setPublic_Place(String public_Place) {
		this.public_Place = public_Place;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCidadeId(Integer cityId) {
		this.cityId = cityId;
	}
}