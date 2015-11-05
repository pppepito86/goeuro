package com.goeuro.exceptions;

import com.goeuro.resources.ErrorCodes;
import com.goeuro.resources.Messages;

public class ClientException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	private int errorCode;
	
	public ClientException(String errorMessageKey, Throwable t, Object...params) {
		super(Messages.getMessage(errorMessageKey, params), t);
		errorCode = ErrorCodes.getErrorCode(errorMessageKey);
	}

	public ClientException(String errorMessageKey, Object... params) {
		this(errorMessageKey, null, params);
	}
	
	public int getErrorCode() {
		return errorCode;
	}

}
