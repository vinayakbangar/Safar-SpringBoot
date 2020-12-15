package com.lti.bean;

import javax.persistence.*;


/**
 * The persistent class for the ADMINS database table.
 * 
 */
@Entity
@Table(name="ADMINS")
public class Admin {

	@Id
	@Column(name="ADMIN_ID")
	private String adminId;

	@Column(name="ADMIN_EMAIL")
	private String adminEmail;

	@Column(name="ADMIN_PASSWORD")
	private String adminPassword;

	public Admin() {
	}

	public String getAdminId() {
		return this.adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminEmail() {
		return this.adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

}