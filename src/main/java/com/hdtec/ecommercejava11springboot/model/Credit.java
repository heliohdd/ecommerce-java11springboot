package com.hdtec.ecommercejava11springboot.model;

import javax.persistence.Entity;

import com.hdtec.ecommercejava11springboot.model.enums.PaymentStatus;

@Entity
public class Credit extends Payment {		// PagamentoComCartão
	private static final long serialVersionUID = 1L;
		
	private Integer instances;

	public Credit() {
	}

	public Credit(Integer id, PaymentStatus status, Demand demand, Integer instances) {
		super(id, status, demand);
		this.instances = instances;
	}

	public Integer getInstances() {
		return instances;
	}

	public void setInstances(Integer instances) {
		this.instances = instances;
	}

}