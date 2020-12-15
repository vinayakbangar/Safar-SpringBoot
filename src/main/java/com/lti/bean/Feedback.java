package com.lti.bean;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the FEEDBACK database table.
 * 
 */
@Entity
@Table(name="FEEDBACK")
public class Feedback{

	@Id
	@Column(name="FEEDBACK_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="FEED_GEN")
    @SequenceGenerator(name="FEED_GEN",sequenceName="feed_seq",allocationSize=1)
	private long feedbackId;

	@Column(name="FEEDBACK_RECEIVED")
	private String feedbackReceived;

	// many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMAIL")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private User user;

	public Feedback() {
	}

	public long getFeedbackId() {
		return this.feedbackId;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getFeedbackReceived() {
		return this.feedbackReceived;
	}

	public void setFeedbackReceived(String feedbackReceived) {
		this.feedbackReceived = feedbackReceived;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}