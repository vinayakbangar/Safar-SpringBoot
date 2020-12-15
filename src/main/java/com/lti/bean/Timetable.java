package com.lti.bean;







import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the TIMETABLE database table.
 * 
 */
@Entity
@Table(name="TIMETABLE")
public class Timetable{

	@Id
	@Column(name="TIMETABLE_ID")
	private String timetableId;

	@Column(name="DEPARTURE_DATE_TIME")
	private Date departureDateTime;

	//bi-directional many-to-one association to Bus
	@ManyToOne
	@JoinColumn(name="BUS_NO")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Bus bus;

	//bi-directional many-to-one association to Driver
	@ManyToOne
	@JoinColumn(name="DRIVER_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Driver driver;

	//bi-directional many-to-one association to Route
	@ManyToOne
	@JoinColumn(name="ROUTE_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Route route;

	public Timetable() {
	}

	public String getTimetableId() {
		return this.timetableId;
	}

	public void setTimetableId(String timetableId) {
		this.timetableId = timetableId;
	}

	public Date getDepartureDateTime() {
		return this.departureDateTime;
	}

	public void setDepartureDateTime(Date departureDateTime) {
		this.departureDateTime = departureDateTime;
	}

	public Bus getBus() {
		return this.bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public Driver getDriver() {
		return this.driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Route getRoute() {
		return this.route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

}