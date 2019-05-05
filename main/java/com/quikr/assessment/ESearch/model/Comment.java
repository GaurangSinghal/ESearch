package com.quikr.assessment.ESearch.model;

import java.util.Date;

public class Comment{
	private String emailId;
	private String comment;
	private Date date;

	public Comment(String emailId, String comment, Date date) {
		super();
		this.emailId = emailId;
		this.comment = comment;
		this.date = date;
	}
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "Comment [emailId=" + emailId + ", comment=" + comment + ", date=" + date + "]";
	}	
}
