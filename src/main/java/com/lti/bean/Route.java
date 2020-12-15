package com.lti.bean;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the ROUTES database table.
 * 
 */
@Entity
@Table(name="ROUTES")
public class Route{

	@Id
	@Column(name="ROUTE_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ROUTE_GEN")
    @SequenceGenerator(name="ROUTE_GEN",sequenceName="route_seq",allocationSize=1)
	private long routeId;
	
	@Column(name="DESTINATION")
	private String destination;

	@Column(name="ROUTE_FARE")
	private BigDecimal routeFare;
	
	@Column(name="SOURCE")
	private String source;
	
	public Route() {
	}
	
	

	public Route(String destination, BigDecimal routeFare, String source) {
		super();
		this.destination = destination;
		this.routeFare = routeFare;
		this.source = source;
	}



	public long getRouteId() {
		return this.routeId;
	}

	public void setRouteId(long routeId) {
		this.routeId = routeId;
	}

	public String getDestination() {
		return this.destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public BigDecimal getRouteFare() {
		return this.routeFare;
	}

	public void setRouteFare(BigDecimal routeFare) {
		this.routeFare = routeFare;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}