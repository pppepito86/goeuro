package com.goeuro.entities;

public class GeoPosition {
	
	private String latitude;
	private String longitude;
	
	public GeoPosition(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}

}
