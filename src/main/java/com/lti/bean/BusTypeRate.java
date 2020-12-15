package com.lti.bean;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BUS_TYPE_RATE database table.
 * 
 */
@Entity
@Table(name="BUS_TYPE_RATE")
public class BusTypeRate {

	@Id
	@Column(name="BUS_TYPE_ID")
	private long busTypeId;

	@Column(name="RATE_PER_SEAT")
	private BigDecimal ratePerSeat;

	@Column(name="TYPE_NAME")
	private String typeName;

	public BusTypeRate() {
	}

	public long getBusTypeId() {
		return this.busTypeId;
	}

	public void setBusTypeId(long busTypeId) {
		this.busTypeId = busTypeId;
	}

	public BigDecimal getRatePerSeat() {
		return this.ratePerSeat;
	}

	public void setRatePerSeat(BigDecimal ratePerSeat) {
		this.ratePerSeat = ratePerSeat;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

}