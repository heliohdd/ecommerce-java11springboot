package com.hdtec.ecommercejava11springboot.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DemandItem implements Serializable {		//	ItemPedido
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private DemandItemPK id = new DemandItemPK();

	private Double descount;
	private Integer quantity;
	private Double price;

	public DemandItem() {
	}

	public DemandItem(Demand demand, Product product, Double descount, Integer quantity, Double price) {
		super();
		id.setDemand(demand);
		id.setProduct(product);
		this.descount = descount;
		this.quantity = quantity;
		this.price = price;
	}

	public double getSubtotal() {
		return (price - descount) * quantity;
	}
	
	@JsonIgnore
	public Demand getDemand() {
		return id.getDemand();
	}
	
	public void setDemand(Demand demand) {
		id.setDemand(demand);
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public DemandItemPK getId() {
		return id;
	}

	public void setId(DemandItemPK id) {
		this.id = id;
	}

	public Double getDescount() {
		return descount;
	}

	public void setDescount(Double descount) {
		this.descount = descount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DemandItem other = (DemandItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}