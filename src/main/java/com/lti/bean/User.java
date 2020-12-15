package com.lti.bean;

import java.math.BigDecimal;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User{
	

	@Id
	private String email;
	
	@Column(name="CONTACT_NUMBER")
	private BigDecimal contactNumber;
	
	@Column(name="DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="REGISTERED")
	private char registered;
	
	@Column(name="WALLET")
	private BigDecimal wallet;


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(BigDecimal contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public char getRegistered() {
		return registered;
	}

	public void setRegistered(char registered) {
		this.registered = registered;
	}

	public BigDecimal getWallet() {
		return wallet;
	}

	public void setWallet(BigDecimal wallet) {
		this.wallet = wallet;
	}
	
}
