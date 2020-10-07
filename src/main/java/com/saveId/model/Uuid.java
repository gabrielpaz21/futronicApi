package com.saveId.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
public class Uuid {
	
	@Id 
	@GeneratedValue
	private Long uuid_id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name="license")
	private Client client;
	
	@NotBlank
	@Column(unique = true)
	private String uuid;
}
