package com.goeuro.resources;

import java.util.ResourceBundle;

public class InitProperties {
	
	public static final String CSV_FILE_NAME = "csv_file_name";
	public static final String REST_URL_PATTERN = "rest_url_pattern";
	private static final String INIT_PROPERTIES_BUNDLE = "init";

	public static String getInitProperty(String key) {
		return ResourceBundle.getBundle(INIT_PROPERTIES_BUNDLE).getString(key);
	}
	
	public static String getCSVFileName() {
		return getInitProperty(CSV_FILE_NAME);
	}

	public static String getRestUrlPattern() {
		return getInitProperty(REST_URL_PATTERN);
	}
}
