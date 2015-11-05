package com.goeuro;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Before;
import org.junit.Test;

import com.goeuro.exceptions.ClientException;

public class RestCallerTest {

	public static final String CITY_NAME = "berlin";

	private RestCaller restCaller;
	private HttpResponse response;

	@Before
	public void before() {
		restCaller = spy(new RestCaller(CITY_NAME));
		response = mock(CloseableHttpResponse.class);
	}

	@Test
	public void testResponseOK() throws Exception {
		String responseString = "response.string";
		setStatusLineOK();
		setResponseString(responseString);
		doReturn(response).when(restCaller).getResponse(any(CloseableHttpClient.class), any(HttpGet.class));
		String jsonString = restCaller.downloadJson();
		assertThat(jsonString, is(responseString));
	}

	@Test
	public void testResponseStatusNotOK() throws Exception {
		setStatusLineNotOK();
		doReturn(response).when(restCaller).getResponse(any(CloseableHttpClient.class), any(HttpGet.class));
		try {
			restCaller.downloadJson();
		} catch (ClientException ce) {
			assertThat(ce.getMessage(),
					is("Response from http://api.goeuro.com/api/v2/position/suggest/en/berlin is not okay"));
			assertThat(ce.getErrorCode(), is(6));
			return;
		}
		fail("ClientException is expected");
	}

	@Test
	public void testRequestUrlProblem() throws Exception {
		setStatusLineOK();
		doThrow(new IOException()).when(restCaller).getResponse(any(CloseableHttpClient.class), any(HttpGet.class));
		try {
			restCaller.downloadJson();
		} catch (ClientException ce) {
			assertThat(ce.getMessage(),
					is("Problem accessing url: http://api.goeuro.com/api/v2/position/suggest/en/berlin"));
			assertThat(ce.getErrorCode(), is(7));
			return;
		}
		fail("ClientException is expected");
	}

	private void setResponseString(String responseString) throws UnsupportedEncodingException {
		StringEntity entity = new StringEntity(responseString);
		when(response.getEntity()).thenReturn(entity);
	}

	private void setStatusLineOK() {
		setStatusLineOK(HttpStatus.SC_OK);
	}

	private void setStatusLineNotOK() {
		setStatusLineOK(HttpStatus.SC_INTERNAL_SERVER_ERROR);
	}

	private void setStatusLineOK(int responseCode) {
		StatusLine statusLine = mock(StatusLine.class);
		when(statusLine.getStatusCode()).thenReturn(responseCode);
		when(response.getStatusLine()).thenReturn(statusLine);
	}

}
