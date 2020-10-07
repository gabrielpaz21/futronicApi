package com.saveId.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.saveId.exception.ClientNotFoundException;
import com.saveId.exception.MaxDeviceException;
import com.saveId.model.Client;
import com.saveId.model.Uuid;
import com.saveId.repository.ClientRepository;
import com.saveId.service.base.BaseService;

@Service
public class ClientService extends BaseService<Client, String, ClientRepository> {

	public boolean x(String license, String uuid) throws ClientNotFoundException, MaxDeviceException{

		Client client = this.findById(license).orElseThrow(() -> new ClientNotFoundException(license));

		Set<Uuid> uuids = client.getUuids();

		if (uuids.size() < client.getDevices_count()) {

			if (validate_uuid(uuid, uuids)) {

				return true;

			} else {

				client.addUuid(Uuid.builder().client(client).uuid(uuid).build());

				this.save(client);

				return true;

			}

		} else {
			
			if(validate_uuid(uuid, uuids)) {
				
				return true;
				
			}else {
				
				throw new MaxDeviceException(license);
			}
			
		}

	}

	private boolean validate_uuid(String uuid_v, Set<Uuid> uuids) {

		Boolean aux = uuids.stream().anyMatch(uuid -> uuid.getUuid().equals(uuid_v));

		return aux;
	}

}
