package com.telcomsis.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telcomsis.dto.ClientResponse;
import com.telcomsis.dto.Login;
import com.telcomsis.dto.RegisterRequest;
import com.telcomsis.dto.ValidateUserRequest;
import com.telcomsis.dto.ValidateUserResponse;
import com.telcomsis.exception.RegisterNotFoundException;
import com.telcomsis.exception.UserNotFoundException;
import com.telcomsis.model.Register;
import com.telcomsis.model.User;
import com.telcomsis.service.RegisterService;
import com.telcomsis.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class controllerRegister {

	@Autowired
	private final UserService userService;

	@Autowired
	private final RegisterService registerService;

	@PostMapping("login")
	public ClientResponse login(@Valid @RequestBody Login login) throws UserNotFoundException {

		boolean authorized = false;

		Optional<User> userVerification = userService.findByUsername(login.getUsername());

		if (!userVerification.isPresent()) {
			throw new UserNotFoundException();
		}

		User user = userVerification.get();

		if (user.getUsername().equals(login.getUsername()) && user.getPassword().equals(login.getPassword())) {
			authorized = true;
		}

		return ClientResponse.builder().authorized(authorized).build();
	}

	@PostMapping("save_data")
	public ResponseEntity<RegisterRequest> cambiarNombre(@Valid @RequestBody RegisterRequest registerRequest) {

		Register register = Register.builder().firstName(registerRequest.getFirstName())
				.lastName(registerRequest.getLastName()).dni(registerRequest.getDni())
				.templateRightThumbFinger(registerRequest.getTemplateRightThumbFinger())
				.bitMapRightThumbFinger(registerRequest.getBitMapRightThumbFinger())
				.templateRightIndexFinger(registerRequest.getTemplateRightIndexFinger())
				.bitMapRightIndexFinger(registerRequest.getBitMapRightIndexFinger()).build();

		registerService.save(register);

		return ResponseEntity.status(HttpStatus.CREATED).body(registerRequest);
	}

	@PostMapping("validate_user")
	public ValidateUserResponse validateUser(@Valid @RequestBody ValidateUserRequest validateUserRequest) {

		boolean validate = false;	

		Optional<Register> registerVerification = registerService.findByDni(validateUserRequest.getDni());

		if (!registerVerification.isPresent()) {
			validate = true;
		}

		ValidateUserResponse validateUser = ValidateUserResponse.builder().validate(validate).build();

		return validateUser;
	}
	
	@GetMapping("{dni}")
	public Register getRegisterByDni(@Valid @PathVariable String dni) throws RegisterNotFoundException {

		Optional<Register> registerVerification = registerService.findByDni(dni);
		
		if (!registerVerification.isPresent()) {
			throw new RegisterNotFoundException();
		}

		Register register = registerVerification.get();

		return register;
	}
	
	@GetMapping()
	public List<Register> getAllRegister(){

		List<Register> registers = registerService.findAll();

		return registers;
	}
	
}
