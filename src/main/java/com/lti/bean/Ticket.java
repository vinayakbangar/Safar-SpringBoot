package com.lti.bean;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;


/**
 * The persistent class for the TICKET database table.
 * 
 */
@Entity
@Table(name="TICKET")
public class Ticket{


	@Id
	@Column(name="TICKET_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="TICK_GEN")
    @SequenceGenerator(name="TICK_GEN",sequenceName="tick_seq",allocationSize=1)
	private long ticketId;
	
	@Column(name="FARE")
	private BigDecimal fare;

	@Column(name="JOURNEY_TYPE")
	private String journeyType;
	
	@Column(name="SEAT_NUMBERS")
	private String seatNumbers;
	
	// many-to-one association to Timetable
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TIMETABLE_ID")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Timetable timetable;

	// many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMAIL")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;

	public Ticket() {
	}

	public long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public BigDecimal getFare() {
		return this.fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	public String getJourneyType() {
		return this.journeyType;
	}

	public void setJourneyType(String journeyType) {
		this.journeyType = journeyType;
	}
	
	
	public String getSeatNumbers() {
		return seatNumbers;
	}

	public void setSeatNumbers(String seatNumbers) {
		this.seatNumbers = seatNumbers;
	}

	public Timetable getTimetable() {
		return this.timetable;
	}

	public void setTimetable(Timetable timetable) {
		this.timetable = timetable;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}