package com.hdtec.ecommercejava11springboot.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.hdtec.ecommercejava11springboot.controller.exception.FieldMessage;
import com.hdtec.ecommercejava11springboot.dto.ClientDTO;
import com.hdtec.ecommercejava11springboot.model.Client;
import com.hdtec.ecommercejava11springboot.repositories.ClientRepository;

public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate, ClientDTO> {

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClientRepository clientRepository ;

	@Override
	public void initialize(ClientUpdate ann) {
	}

	@Override
	public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));

		List<FieldMessage> list = new ArrayList<>();

		Client aux = clientRepository.findByEmail(objDto.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			list.add(new FieldMessage("email", "Existing email."));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}