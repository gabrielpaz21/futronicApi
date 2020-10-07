package com.saveId.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @NoArgsConstructor @AllArgsConstructor 
@Entity
@Builder
public class Client {
	
	@Id 
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
	
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@JsonManagedReference
	@Builder.Default
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Uuid> uuids = new HashSet<>();
	
	public void addUuid(Uuid uuid) {
		uuids.add(uuid);
		uuid.setClient(this);
	}
	
	public void removeUuid(Uuid uuid) {
		uuids.remove(uuid);
		uuid.setClient(null);
	}
	
}
