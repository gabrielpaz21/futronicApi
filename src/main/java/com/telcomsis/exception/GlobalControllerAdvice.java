package com.telcomsis.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {
	
	private String mensageCliente;
	
	private String mensageProgramador;

	@ExceptionHandler({ UserNotFoundException.class })
	public ResponseEntity<ApiError> clientNotFoundException(Exception ex) {
		mensageCliente= ex.getMessage();
		mensageProgramador=ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, mensageProgramador,mensageCliente);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(apiError);
	}
	
	@ExceptionHandler({ RegisterNotFoundException.class })
	public ResponseEntity<ApiError> registerNotFoundException(Exception ex) {
		mensageCliente= ex.getMessage();
		mensageProgramador=ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, mensageProgramador,mensageCliente);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<ApiError> dataIntegrityViolation(Exception ex) {
		mensageCliente=ex.getMessage();
		mensageProgramador=ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, mensageProgramador,mensageCliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}

	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<ApiError> constraintViolationException(Exception ex) {
		mensageCliente=ex.getMessage();
		mensageProgramador=ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, mensageProgramador,mensageCliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	
	@ExceptionHandler({ PrimaryKeyconstraintViolationException.class })
	public ResponseEntity<ApiError> primaryKeyconstraintViolationException(Exception ex) {
		mensageCliente = ex.getMessage();
		mensageProgramador=ex.getMessage();
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, mensageProgramador,mensageCliente);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
	}
	 
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		mensageProgramador = ex.getMessage();
		
		if(mensageProgramador.contains("username") && mensageProgramador.contains("password")) {
			
			mensageCliente="El usuario y la contraseña no pueden estar vacíos.";
		}else if(mensageProgramador.contains("username")) {
			
			mensageCliente="El nombre de usuario no puede estar vacío.";
		}else if (mensageProgramador.contains("password")) {
			
			mensageCliente="La contraseña no puede estar vacía.";
		}
		
		ApiError apiError = new ApiError(status,mensageProgramador,mensageCliente);
		return ResponseEntity.status(status).body(apiError);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ApiError apiError = new ApiError(status, ex.getMessage(),"Error desconocido");
		return ResponseEntity.status(status).headers(headers).body(apiError);
	}
	
}
