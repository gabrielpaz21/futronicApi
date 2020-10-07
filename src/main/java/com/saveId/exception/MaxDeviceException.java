package com.saveId.exception;

public class MaxDeviceException extends Exception{

	private static final long serialVersionUID = 8150128396067202824L;
	
	public MaxDeviceException(String id_client) {
		
		super("La licencia '" + id_client  + "' ha alcanzado el maximo de dispositivos asociados.");
		
	}
}
