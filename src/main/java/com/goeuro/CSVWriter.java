package com.goeuro;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.goeuro.entities.Place;
import com.goeuro.exceptions.ClientException;
import com.goeuro.resources.InitProperties;
import com.goeuro.resources.Messages;

public class CSVWriter {
	
	private static final String CSV_FILE_NAME = InitProperties.getCSVFileName();
	
	public static void write(List<Place> places) {
		File file = new File(CSV_FILE_NAME);
		try (FileWriter fw = new FileWriter(file)) {
			for (Place place : places) {
				fw.append(place.getCSVRecord());
				fw.append(System.lineSeparator());
			}
		} catch (IOException e) {
			throw new ClientException(Messages.CSV_WRITE_PROBLEM, e, file.getAbsolutePath());
		}
		System.out.println(Messages.getMessage(Messages.CSV_FILE_GENERATED, file.getAbsolutePath()));
	}

}
