package com.goeuro;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import com.goeuro.entities.Place;

public class TestUtil {

	public static List<Place> getPlaces(Class<?> _class) {
		String s = getResourceAsString(_class, "/berlin.json");
		return JsonParser.parse(s);
	}

	public static String getResourceAsString(Class<?> _class, String fileName) {
		String s;
		try (Scanner in = new Scanner(_class.getResourceAsStream(fileName))) {
			s = in.useDelimiter("\\A").next();
		}
		return s;
	}

	public static String getFileAsString(Class<?> _class, File file) throws FileNotFoundException {
		String s;
		try (Scanner in = new Scanner(file)) {
			s = in.useDelimiter("\\A").next();
		}
		return s;
	}

}
