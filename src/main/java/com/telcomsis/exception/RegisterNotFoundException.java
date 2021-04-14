package com.telcomsis.exception;

public class RegisterNotFoundException extends Exception {

	private static final long serialVersionUID = -2730111931833397903L;

	public RegisterNotFoundException() {
		super("El registro no fue encontrado.");
	}
	
}
