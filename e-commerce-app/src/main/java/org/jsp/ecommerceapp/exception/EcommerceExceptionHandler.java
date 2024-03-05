package org.jsp.ecommerceapp.exception;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
public class EcommerceExceptionHandler extends RuntimeException{

	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(InvalidCredentialException exception){
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setBody("cannot find merchant");
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleICE(IdNotFoundException exception){
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setBody("cannot find merchant");
			structure.setMessage(exception.getMessage());
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(MerchantNotFoundException.class)
		public ResponseEntity<ResponseStructure<String>> handleICE(MerchantNotFoundException exception){
				ResponseStructure<String> structure=new ResponseStructure<>();
				structure.setBody("cannot find merchant");
				structure.setMessage(exception.getMessage());
				structure.setStatusCode(HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			}
			
			@ExceptionHandler(UserNotFoundException.class)
		public ResponseEntity<ResponseStructure<String>> handleICE(UserNotFoundException exception){
				ResponseStructure<String> structure=new ResponseStructure<>();
				structure.setBody("cannot find merchant");
				structure.setMessage(exception.getMessage());
				structure.setStatusCode(HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
			}
			
			
	}
