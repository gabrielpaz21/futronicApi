package com.saveId.service;

import org.springframework.stereotype.Service;

import com.saveId.model.Uuid;
import com.saveId.repository.UuidRepository;
import com.saveId.service.base.BaseService;

@Service
public class UuidService extends BaseService<Uuid, Long, UuidRepository> {
	

}
