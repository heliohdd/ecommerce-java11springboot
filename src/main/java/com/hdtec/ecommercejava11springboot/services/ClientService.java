package com.hdtec.ecommercejava11springboot.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hdtec.ecommercejava11springboot.dto.ClientDTO;
import com.hdtec.ecommercejava11springboot.dto.ClientNewDTO;
import com.hdtec.ecommercejava11springboot.model.Address;
import com.hdtec.ecommercejava11springboot.model.City;
import com.hdtec.ecommercejava11springboot.model.Client;
import com.hdtec.ecommercejava11springboot.model.enums.ClientType;
import com.hdtec.ecommercejava11springboot.repositories.AddressRepository;
import com.hdtec.ecommercejava11springboot.repositories.ClientRepository;
import com.hdtec.ecommercejava11springboot.services.exceptions.DataIntegrityException;
import com.hdtec.ecommercejava11springboot.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {		//	ClienteService

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	public Client find(Integer id) {
		Optional<Client> obj = clientRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAddresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Cannot delete because there are related entities.");
		}
	}
	
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	public Client fromDTO(@Valid ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client cli = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOuCnpj(), ClientType.toEnum(objDto.getType()));
		City cit = new City(objDto.getCityId(), null, null);
		Address end = new Address(null, objDto.getPublic_Place(), objDto.getNumber(), objDto.getComplement(), objDto.getDistrict(), objDto.getZip_code(), cli, cit);
		cli.getAddresses().add(end);
		cli.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2()!=null) {
			cli.getPhones().add(objDto.getPhone2());
		}
		if (objDto.getPhone3()!=null) {
			cli.getPhones().add(objDto.getPhone3());
		}
		return cli;
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}	
}