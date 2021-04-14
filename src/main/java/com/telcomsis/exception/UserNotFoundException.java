package com.telcomsis.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -2730111931833397903L;

	public UserNotFoundException() {
		super("El usuario o la contraseña no son válidos.");
	}
	
}
