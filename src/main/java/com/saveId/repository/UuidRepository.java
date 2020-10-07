package com.saveId.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.saveId.model.Uuid;

@Repository
public interface UuidRepository extends JpaRepository<Uuid, Long> {
	

}
