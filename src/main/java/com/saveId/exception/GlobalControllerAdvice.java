package com.saveId.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
	
	private String mensage_cliente;

	@ExceptionHandler({ ClientNotFoundException.class })
	public ResponseEntity<ApiError> clientNotFoundException(Exception ex) {
		mensage_cliente= ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(),mensage_cliente);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<ApiError> dataIntegrityViolation(Exception ex) {
		mensage_cliente="Ha ocurrido un error, por favor contacte a soperte.";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),mensage_cliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ApiError> constraintViolationException(Exception ex) {
		mensage_cliente="La licencia ingresada no es valida.";
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),mensage_cliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler({ PrimaryKeyconstraintViolationException.class })
	public ResponseEntity<ApiError> primaryKeyconstraintViolationException(Exception ex) {
		mensage_cliente= ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),mensage_cliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler({ MaxDeviceException.class })
	public ResponseEntity<ApiError> maxDeviceException(Exception ex) {
		mensage_cliente= ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),mensage_cliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage(),"Error desconocido");
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
}
