package com.saveId.exception;

public class ClientNotFoundException extends Exception {


	private static final long serialVersionUID = -2730111931833397903L;

	public ClientNotFoundException(String client_id) {
		super("La licencia '" + client_id + "' no es valida.");
	}
	
	
}
