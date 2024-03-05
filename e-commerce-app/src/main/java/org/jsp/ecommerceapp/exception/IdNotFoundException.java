package org.jsp.ecommerceapp.exception;

public class IdNotFoundException extends RuntimeException {
	public String getMessage() {
		return "Invalid Id";
		}

}
