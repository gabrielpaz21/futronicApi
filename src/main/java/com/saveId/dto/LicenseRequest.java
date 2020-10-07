package com.saveId.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LicenseRequest {
	
	@NotBlank
	private String license;
	
	@NotBlank
	private String full_name;
	
	@NotNull
	private Long number_phone;
	
	@NotBlank
	private String email;
	
	@NotNull
	private Long devices_count;

}
