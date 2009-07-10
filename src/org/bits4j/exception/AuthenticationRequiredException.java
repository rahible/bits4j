package org.bits4j.exception;

public class AuthenticationRequiredException extends AckNotFoundException {

	private static final long serialVersionUID = 3187660023038373057L;

	// WWW-Authenticate

	public AuthenticationRequiredException(String message) {
		super(message);
	}
}
