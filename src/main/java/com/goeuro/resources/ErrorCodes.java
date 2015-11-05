package com.goeuro.resources;

import java.util.ResourceBundle;

public class ErrorCodes {
	
	public static final int DEFAULT_ERROR_CODE = 1;

	private static final String ERROR_CODES_BUNDLE = "error_codes";

	public static int getErrorCode(String errorMessageKey) {
		String errorCode = ResourceBundle.getBundle(ERROR_CODES_BUNDLE).getString(errorMessageKey);
		try {
			return Integer.parseInt(errorCode);
		} catch (NumberFormatException nfe) {
			return DEFAULT_ERROR_CODE;
		}
	}
}
