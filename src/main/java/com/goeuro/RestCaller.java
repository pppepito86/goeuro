package com.goeuro;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.goeuro.exceptions.ClientException;
import com.goeuro.resources.InitProperties;
import com.goeuro.resources.Messages;

public class RestCaller {
	
	private static Logger logger = Logger.getLogger(RestCaller.class.getName());
	
	private static final String URL_PATTERN = InitProperties.getRestUrlPattern();
	
	private String url;
	
	public RestCaller(String city) {
		this.url = String.format(URL_PATTERN, city);
		logger.info("Api url is: " + url);
	}
	
	public String downloadJson() throws IOException {
		try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet request = createRequest();
			HttpResponse response = getResponse(httpClient, request);
			checkStatusOK(response);
			String jsonString = getResponseString(response);
			logger.debug("Json Response is: " + jsonString);
			return jsonString;
		} catch (IOException e) {
			throw new ClientException(Messages.URL_ACCESS_PROBLEM, e, url);
		}
	}
	
	private HttpGet createRequest() {
		HttpGet request = new HttpGet(url);
		request.addHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());
		return request;
	}

	CloseableHttpResponse getResponse(CloseableHttpClient httpClient, HttpGet request)
			throws IOException, ClientProtocolException {
		return httpClient.execute(request);
	}

	private void checkStatusOK(HttpResponse response) {
		if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
			throw new ClientException(Messages.RESPONSE_NOT_OK, url);
		}
	}

	private String getResponseString(HttpResponse response) throws IOException {
		HttpEntity entity = response.getEntity();
		String jsonString = EntityUtils.toString(entity);
		return jsonString;
	}

}
