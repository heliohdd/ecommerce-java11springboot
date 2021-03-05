package com.hdtec.ecommercejava11springboot.model;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hdtec.ecommercejava11springboot.model.enums.PaymentStatus;

@Entity
public class Billet extends Payment {		// PagamentoComBoleto
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dueDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;

	public Billet() {
	}

	public Billet(Integer id, PaymentStatus status, Demand demand, Date dueDate, Date paymentDate) {
		super(id, status, demand);
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}