package com.goeuro;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeFalse;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.goeuro.entities.Place;
import com.goeuro.resources.InitProperties;

public class CSVWriterTest {
	
	@Test
	public void test() throws Exception {
		File file = new File(InitProperties.getCSVFileName());
		if (file.exists()) {
			System.out.println("File " + file.getAbsolutePath() + " exists. Test will be skipped");
		}
		assumeFalse(file.exists());

		List<Place> places = TestUtil.getPlaces(getClass());
		CSVWriter.write(places);
		assertThat(file.exists(), is(true));
		String actual = TestUtil.getFileAsString(getClass(), file);
		String expected = TestUtil.getResourceAsString(getClass(), "/expected.csv");
		assertThat(actual, is(expected));
		file.delete();
	}

}
