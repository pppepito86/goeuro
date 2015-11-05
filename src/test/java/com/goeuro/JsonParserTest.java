package com.goeuro;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.goeuro.entities.Place;
import com.goeuro.exceptions.ClientException;

public class JsonParserTest {
	
	@Test
	public void testParsePositive() throws Exception {
		List<Place> places = TestUtil.getPlaces(getClass());
		assertThat(places.size(), is(2));
		assertPlace(places.get(0), 377078, "Potsdam", "location", "52.39886", "13.06566");
	}

	@Test
	public void testParseNegative() {
		String s = "illegal json";
		try {
			JsonParser.parse(s);
		} catch(ClientException ce) {
			assertThat(ce.getMessage(), is("Problem parsing json"));
			assertThat(ce.getErrorCode(), is(4));
			return;
		}
		fail("ClientException is expected");
	}
	
	private void assertPlace(Place place, int id, String name, String type, String latitude, String longitude) {
		assertThat(place.getId(), is(id));
		assertThat(place.getName(), is(name));
		assertThat(place.getType(), is(type));
		assertThat(place.getGeoPosition().getLatitude(), is(latitude));
		assertThat(place.getGeoPosition().getLongitude(), is(longitude));
	}

}
