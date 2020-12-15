package com.lti.bean;

import javax.persistence.*;


/**
 * The persistent class for the DRIVER database table.
 * 
 */
@Entity
@Table(name="DRIVER")
public class Driver{

	@Id
	@Column(name="DRIVER_ID")
	private String driverId;

	@Column(name="DRIVER_AVAILABLE")
	private char driverAvailable;

	@Column(name="DRIVER_NAME")
	private String driverName;

	public Driver() {
	}

	public String getDriverId() {
		return this.driverId;
	}

	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	public char getDriverAvailable() {
		return this.driverAvailable;
	}

	public void setDriverAvailable(char driverAvailable) {
		this.driverAvailable = driverAvailable;
	}

	public String getDriverName() {
		return this.driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
}