package com.example.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long long1) {
        super("User with ID " + long1 + " not found");
    }

	public UserNotFoundException(String string) {
		// TODO Auto-generated constructor stub
	}

}