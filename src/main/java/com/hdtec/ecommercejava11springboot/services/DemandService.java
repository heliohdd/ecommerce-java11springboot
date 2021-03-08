package com.hdtec.ecommercejava11springboot.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hdtec.ecommercejava11springboot.model.Billet;
import com.hdtec.ecommercejava11springboot.model.Demand;
import com.hdtec.ecommercejava11springboot.model.DemandItem;
import com.hdtec.ecommercejava11springboot.model.enums.PaymentStatus;
import com.hdtec.ecommercejava11springboot.repositories.DemandItemRepository;
import com.hdtec.ecommercejava11springboot.repositories.DemandRepository;
import com.hdtec.ecommercejava11springboot.repositories.PaymentRepository;
import com.hdtec.ecommercejava11springboot.services.exceptions.ObjectNotFoundException;

@Service
public class DemandService {		//	PedidoService
	
	@Autowired
	private DemandRepository demandRepository;
	

	@Autowired
	private BilletService billetService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private DemandItemRepository demandItemRepository;

	@Autowired
	private ProductService productService;

	public Demand find(Integer id) {
		Optional<Demand> obj = demandRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Demand.class.getName()));
	}

	public Demand insert(Demand obj) {
			obj.setId(null);
			obj.setInstant(new Date());
			obj.getPayment().setStatus(PaymentStatus.PENDING);
			obj.getPayment().setDemand(obj);
			if (obj.getPayment() instanceof Billet) {
				Billet pagto = (Billet) obj.getPayment();
				billetService.preencherPagamentoComBoleto(pagto, obj.getInstant());
			}
			obj = demandRepository.save(obj);
			paymentRepository.save(obj.getPayment());
			for (DemandItem di : obj.getItens()) {
				di.setDescount(0.0);
				di.setPrice(productService.find(di.getProduct().getId()).getPrice());
				di.setDemand(obj);
			}
			demandItemRepository.saveAll(obj.getItens());
			return obj;
		}
}