package com.hdtec.ecommercejava11springboot;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hdtec.ecommercejava11springboot.model.Address;
import com.hdtec.ecommercejava11springboot.model.Billet;
import com.hdtec.ecommercejava11springboot.model.Category;
import com.hdtec.ecommercejava11springboot.model.City;
import com.hdtec.ecommercejava11springboot.model.Client;
import com.hdtec.ecommercejava11springboot.model.Credit;
import com.hdtec.ecommercejava11springboot.model.Demand;
import com.hdtec.ecommercejava11springboot.model.Payment;
import com.hdtec.ecommercejava11springboot.model.Product;
import com.hdtec.ecommercejava11springboot.model.State;
import com.hdtec.ecommercejava11springboot.model.enums.ClientType;
import com.hdtec.ecommercejava11springboot.model.enums.PaymentStatus;
import com.hdtec.ecommercejava11springboot.repositories.AddressRepository;
import com.hdtec.ecommercejava11springboot.repositories.CategoryRepository;
import com.hdtec.ecommercejava11springboot.repositories.CityRepository;
import com.hdtec.ecommercejava11springboot.repositories.ClientRepository;
import com.hdtec.ecommercejava11springboot.repositories.DemandRepository;
import com.hdtec.ecommercejava11springboot.repositories.PaymentRepository;
import com.hdtec.ecommercejava11springboot.repositories.ProductRepository;
import com.hdtec.ecommercejava11springboot.repositories.StateRepository;

@SpringBootApplication
public class EcommerceJava11springbootApplication implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private DemandRepository demandRepository  ;
	@Autowired
	private PaymentRepository paymentRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceJava11springbootApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");

		Product p1 = new Product(null, "Computador", 2000.00);
		Product p2 = new Product(null, "Impressora", 800.00);
		Product p3 = new Product(null, "Mouse", 80.00);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));

		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");

		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		

		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "36378912377", ClientType.PHYSICALPERSON);

		cli1.getPhones().addAll(Arrays.asList("27363323", "93838393"));

		Address e1 = new Address(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Address e2 = new Address(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getAddresses().addAll(Arrays.asList(e1, e2));

		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Demand ped1 = new Demand(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Demand ped2 = new Demand(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Payment pagto1 = new Credit(null, PaymentStatus.MADE, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new Billet(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli1.getDemands().addAll(Arrays.asList(ped1, ped2));

		demandRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}
}