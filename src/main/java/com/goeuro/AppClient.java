package com.goeuro;

import java.util.List;

import org.apache.log4j.Logger;

import com.goeuro.entities.Place;
import com.goeuro.exceptions.ClientException;
import com.goeuro.resources.ErrorCodes;
import com.goeuro.resources.Messages;

public class AppClient {
	
	private static Logger logger = Logger.getLogger(AppClient.class.getName());

	public static void main(String[] args) {
		new AppClient().execute(args);
	}

	private void execute(String[] args) {
		try {
			String city = getCity(args);
			String jsonString = new RestCaller(city).downloadJson();
			List<Place> places = JsonParser.parse(jsonString);
			CSVWriter.write(places);
		} catch (ClientException e) {
			handleException(e.getMessage(), e);
		} catch (Exception e) {
			handleException(Messages.PROBLEM_OCCURED, e, ErrorCodes.DEFAULT_ERROR_CODE);
		}
	}

	private String getCity(String[] args) {
		if (args.length == 0) {
			throw new ClientException(Messages.MISSING_CITY);
		} else if (args.length > 1) {
			throw new ClientException(Messages.MORE_INPUT_PARAMS);
		}
		return args[0];
	}

	private void handleException(String errorMessage, ClientException exception) {
		handleException(errorMessage, exception, exception.getErrorCode());
	}
	
	private void handleException(String errorMessage, Exception exception, int exitCode) {
		logger.error(exception.getMessage(), exception);
		System.out.println(errorMessage + ".");
		System.out.println(Messages.getMessage(Messages.MORE_LOGS));
		logger.info("Exiting with code: " + exitCode);
		System.exit(exitCode);
	}

}

