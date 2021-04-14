package com.telcomsis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
@Builder
public class Register {
	
	@Id 
	@GeneratedValue
	@Column(name = "client_id")
	private Long clientId;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@NotBlank
	@Column(unique = true)
	private String dni;
	
	@NotBlank
	@Lob
	private String templateRightThumbFinger;
	
	@NotNull
	@Lob
	private String bitMapRightThumbFinger;
	
	@NotBlank
	@Lob
	private String templateRightIndexFinger;
	
	@NotNull
	@Lob
	private String bitMapRightIndexFinger;
	
	/*
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="user_id", insertable = false, updatable=false)
	private User user;
	*/
	
}
