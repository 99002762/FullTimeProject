package com.studentapp.exceptions;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		String message = ex.getMessage();
		LocalDateTime timeStamp = LocalDateTime.now();
		List<String> details = Arrays.asList("wrong method name", "not supported");
		APIErrors errors = new APIErrors(message, timeStamp, status, details);
		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timeStamp = LocalDateTime.now();
		List<String> details = Arrays.asList("wrong media type", "not supported");
		APIErrors errors = new APIErrors(message, timeStamp, status, details);
		return ResponseEntity.status(status).body(errors);
	}

//	//To Do
//	@Override
//	protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
//			HttpStatus status, WebRequest request) {
//		String message = ex.getMessage();
//		LocalDateTime timeStamp = LocalDateTime.now();
//		List<String> details = Arrays.asList("missing path variable name", "missing path variable");
//		APIErrors errors = new APIErrors(message, timeStamp, status, details);
//		return ResponseEntity.status(status).body(errors);
//	}
//
//	//To Do
//	@Override
//	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
//			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String message = ex.getMessage();
//		LocalDateTime timeStamp = LocalDateTime.now();
//		List<String> details = Arrays.asList("No request param", "missing param ");
//		APIErrors errors = new APIErrors(message, timeStamp, status, details);
//		return ResponseEntity.status(status).body(errors);
//	}



	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String message = ex.getMessage();
		LocalDateTime timeStamp = LocalDateTime.now();
		List<String> details = Arrays.asList("type miss match", "mismatch type ");
		APIErrors errors = new APIErrors(message, timeStamp, status, details);
		return ResponseEntity.status(status).body(errors);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	protected ResponseEntity<Object> handleStudentException(StudentNotFoundException ex) {
		String message = ex.getMessage();
		LocalDateTime timeStamp = LocalDateTime.now();
		List<String> details = Arrays.asList("Student is Not available");
		APIErrors errors = new APIErrors(message, timeStamp, HttpStatus.BAD_REQUEST, details);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

//	@ExceptionHandler(Exception.class)
//	protected ResponseEntity<Object> handleOtherException(Exception ex) {
//		String message = ex.getMessage();
//		LocalDateTime timeStamp = LocalDateTime.now();
//		List<String> details = Arrays.asList(message);
//		APIErrors errors = new APIErrors(message, timeStamp, HttpStatus.INTERNAL_SERVER_ERROR, details);
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errors);
//	}

}
