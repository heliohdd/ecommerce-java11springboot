package com.hdtec.ecommercejava11springboot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hdtec.ecommercejava11springboot.model.enums.PaymentStatus;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment implements Serializable {		//	Pagamento
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private Integer status;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "demand_id")
	@MapsId
	private Demand demand;

	public Payment() {
	}

	public Payment(Integer id, PaymentStatus status, Demand demand) {
		super();
		this.id = id;
		this.status = status.getCod();
		this.demand = demand;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}

	public void setStatus(PaymentStatus status) {
		this.status = status.getCod();
	}

	public Demand getDemand() {
		return demand;
	}

	public void setDemand(Demand demand) {
		this.demand = demand;
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
		Payment other = (Payment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}