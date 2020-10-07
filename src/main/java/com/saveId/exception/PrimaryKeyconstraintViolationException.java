package com.saveId.exception;

public class PrimaryKeyconstraintViolationException extends Exception{

	private static final long serialVersionUID = -99218420780979754L;

	public PrimaryKeyconstraintViolationException(String client_id) {
		super("La licencia '" + client_id + "' ya fue registrada.");
	}
}
