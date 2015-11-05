package com.goeuro.entities;

import com.google.gson.annotations.SerializedName;

public class Place {
	
	@SerializedName("_id")
	private int id;
	private String name;
	private String type;

	@SerializedName("geo_position")
	private GeoPosition geoPosition;
	
	public Place(int id, String name, String type, GeoPosition geoPosition) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.geoPosition = geoPosition;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public GeoPosition getGeoPosition() {
		return geoPosition;
	}
	
	public String getCSVRecord() {
		StringBuilder sb = new StringBuilder();
		sb.append(id).append(",");
		sb.append(name).append(",");
		sb.append(type).append(",");
		sb.append(geoPosition.getLatitude()).append(",");
		sb.append(geoPosition.getLongitude());
		return sb.toString();
	}

}
