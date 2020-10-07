package com.saveId.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saveId.dto.ClientRequest;
import com.saveId.dto.ClientResponse;
import com.saveId.dto.LicenseRequest;
import com.saveId.exception.ClientNotFoundException;
import com.saveId.exception.MaxDeviceException;
import com.saveId.exception.PrimaryKeyconstraintViolationException;
import com.saveId.model.Client;
import com.saveId.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class controllerClient {
	
	private final ClientService clientService;
	
	@PostMapping("validate")
	public ClientResponse save_id(@Valid @RequestBody ClientRequest clientRequest) throws ClientNotFoundException, MaxDeviceException{
		
		
		return ClientResponse.
				builder().
				authorized(
						clientService.x(
								clientRequest.getLicense()
								, clientRequest.getUuid()))
				.build();
		
	}
	
	@PostMapping("create_license")
	public ResponseEntity<LicenseRequest> save_client(@Valid @RequestBody LicenseRequest licenseRequest) throws PrimaryKeyconstraintViolationException{
		
		String license = licenseRequest.getLicense();
		
		Client client = Client.builder()
				.license(license)
				.full_name(licenseRequest.getFull_name())
				.number_phone(licenseRequest.getNumber_phone())
				.email(licenseRequest.getEmail())
				.devices_count(licenseRequest.getDevices_count())
				.build();
				
		Optional<Client> optional = clientService.findById(license);
		
		if(optional.isPresent()) {
			
			throw new PrimaryKeyconstraintViolationException(license);
			
		}else {
			
			clientService.save(client);
		
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(licenseRequest);
		
	}
	
}
