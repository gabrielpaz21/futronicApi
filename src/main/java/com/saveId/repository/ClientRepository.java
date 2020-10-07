package com.saveId.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saveId.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, String>{
	
}
