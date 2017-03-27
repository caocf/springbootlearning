package com.sos.fleet.common.dto;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sos.fleet.common.domain.VehicleDomain;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LocationDto implements Serializable {

	public LocationDto() {
		super();
	}
	
	public LocationDto(String vin, Double speed, Double lat, Double lon,
			Double direction, Integer signOff, Date locatedTime) {
		this.vin = vin;
		this.speed = speed;
		this.lat = lat;
		this.lon = lon;
		this.direction = direction;
		this.signOff = signOff;
		this.locatedTime = locatedTime;
	}

	

	@JsonProperty("vin")
	private String vin;
	@JsonProperty("speed")
	private Double speed;
	@JsonProperty("lat")
	private Double lat;
	@JsonProperty("lon")
	private Double lon;
	@JsonProperty("direction")
	private Double direction;
	@JsonProperty("signOff")
	private Integer signOff;
	@JsonProperty("locatedTime")
	private Date locatedTime;
	private VehicleDomain vehicleDomain;
	
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getDirection() {
		return direction;
	}
	public void setDirection(Double direction) {
		this.direction = direction;
	}
	public Integer getSignOff() {
		return signOff;
	}
	public void setSignOff(Integer signOff) {
		this.signOff = signOff;
	}
	public Date getLocatedTime() {
		return locatedTime;
	}
	public void setLocatedTime(Date locatedTime) {
		this.locatedTime = locatedTime;
	}

	public VehicleDomain getVehicleDomain() {
		return vehicleDomain;
	}

	public void setVehicleDomain(VehicleDomain vehicleDomain) {
		this.vehicleDomain = vehicleDomain;
	}

	public String getLocatedTimeText() {
		if(locatedTime==null)
			return null;
		return DateFormatUtils.format(locatedTime, "yyyy-MM-dd HH:mm:ss");
	}

}
