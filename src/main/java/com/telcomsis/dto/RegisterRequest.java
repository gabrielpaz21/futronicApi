package com.telcomsis.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	private String dni;
	
	@NotBlank
	private String templateRightThumbFinger;
	
	@NotNull
	private String bitMapRightThumbFinger;
	
	@NotBlank
	private String templateRightIndexFinger;
	
	@NotNull
	private String bitMapRightIndexFinger;
	
}
