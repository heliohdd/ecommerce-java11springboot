package com.hdtec.ecommercejava11springboot.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class DemandItemPK implements Serializable {		//	ItemPedidoPK
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name="demand_id")
	private Demand demand;

	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((demand == null) ? 0 : demand.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
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
		DemandItemPK other = (DemandItemPK) obj;
		if (demand == null) {
			if (other.demand != null)
				return false;
		} else if (!demand.equals(other.demand))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}
}