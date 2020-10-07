package com.saveId.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClientRequest {
	
	@NotBlank
	private String license;
	
	@NotBlank
	private String uuid;
	
}
