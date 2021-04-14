package com.telcomsis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
public class User {
	
	@Id 
	@GeneratedValue
	@Column(name = "user_id")
	private Long userId;
	
	@NotBlank
	@Column(unique = true)
	private String username;
	
	@NotBlank
	private String password;
	
	/*
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@JsonManagedReference
	@Builder.Default
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Register> registers = new HashSet<>();
	
	public void addClient(Register register) {
		registers.add(register);
		register.setUser(this);
	}
	
	public void removeClient(Register register) {
		registers.remove(register);
		register.setUser(null);
	}
	*/
}
