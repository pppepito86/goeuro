package com.goeuro.resources;

import java.util.ResourceBundle;

public class Messages {
	
	public static final String MISSING_CITY = "missing_city";
	public static final String MORE_INPUT_PARAMS = "more_input_params";
	public static final String PARSE_JSON_PROBLEM = "parse_json_problem";
	public static final String CSV_WRITE_PROBLEM = "csv_write_problem";
	public static final String PROBLEM_OCCURED = "problem_occured";
	public static final String MORE_LOGS = "more_logs";
	public static final String RESPONSE_NOT_OK = "response_not_ok";
	public static final String URL_ACCESS_PROBLEM = "url_access_problem";
	
	public static final String CSV_FILE_GENERATED = "csv_file_generated";
	
	private static final String MESSAGES_BUNDLE = "messages";
	
	public static String getMessage(String errorMessageKey, Object... params) {
		return String.format(ResourceBundle.getBundle(MESSAGES_BUNDLE).getString(errorMessageKey), params);
	}
}
