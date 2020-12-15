package com.lti.bean;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the BUS database table.
 * 
 */
@Entity
@Table(name="BUS")
public class Bus{

	@Id
	@Column(name="BUS_NO")
	private String busNo;

	@Column(name="BUS_NAME")
	private String busName;

	@Column(name="NO_OF_SEATS")
	private BigDecimal noOfSeats;

	//many-to-one association to BusTypeRate
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BUS_TYPE_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private BusTypeRate busTypeRate;

	public Bus() {
	}

	public String getBusNo() {
		return this.busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getBusName() {
		return this.busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public BigDecimal getNoOfSeats() {
		return this.noOfSeats;
	}

	public void setNoOfSeats(BigDecimal noOfSeats) {
		this.noOfSeats = noOfSeats;
	}

	public BusTypeRate getBusTypeRate() {
		return this.busTypeRate;
	}

	public void setBusTypeRate(BusTypeRate busTypeRate) {
		this.busTypeRate = busTypeRate;
	}

}