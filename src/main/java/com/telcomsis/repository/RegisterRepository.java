package com.telcomsis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.telcomsis.model.Register;


@Repository
public interface RegisterRepository extends JpaRepository<Register, Long>{
	
	public Optional<Register> findByDni(String dni);
}
