package com.goeuro;

import java.util.List;

import com.goeuro.entities.Place;
import com.goeuro.exceptions.ClientException;
import com.goeuro.resources.Messages;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class JsonParser {
	
	public static List<Place> parse(String jsonString) {
		try {
			List<Place> places = new Gson().fromJson(jsonString, new TypeToken<List<Place>>() {
			}.getType());
			return places;
		} catch (JsonSyntaxException jse) {
			throw new ClientException(Messages.PARSE_JSON_PROBLEM, jse);
		}
	}

}
