package io.github.app.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class Codes {
	
	private record Validacao(String campo,String msg) {
		public Validacao(FieldError erro) {
			this(erro.getField(),erro.getDefaultMessage());
		}
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> treat404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<Validacao>> treat400(MethodArgumentNotValidException e) {
		var erros = e.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(Validacao::new).toList());
	}
	
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<Validacao> treat409(SQLIntegrityConstraintViolationException e) {
		var v = new Validacao(String.valueOf(e.getErrorCode()),e.getMessage());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(v);
	}
}
