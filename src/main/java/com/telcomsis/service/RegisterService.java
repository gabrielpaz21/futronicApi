package com.telcomsis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telcomsis.model.Register;
import com.telcomsis.repository.RegisterRepository;
import com.telcomsis.service.base.BaseService;

@Service
public class RegisterService extends BaseService<Register, Long, RegisterRepository> {

	@Autowired
	private RegisterRepository registerRepository;

	public Optional<Register> findByDni(String dni) {
		
		return registerRepository.findByDni(dni);
	}
}
