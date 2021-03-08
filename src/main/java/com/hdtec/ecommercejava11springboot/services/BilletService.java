package com.hdtec.ecommercejava11springboot.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.hdtec.ecommercejava11springboot.model.Billet;

@Service
public class BilletService {

	public void preencherPagamentoComBoleto(Billet pagto, Date demandInstant) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(demandInstant);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDueDate(cal.getTime());
	}
}