package com.poo.uber.model;

import java.util.Date;

public class Race {

	private Local local;
	private Integer feedback;
	private Date date;

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Integer getFeedback() {
		return feedback;
	}

	public void setFeedback(Integer feedback) {
		this.feedback = feedback;
	}

	public double getPrice() {
		double pricePerKm = 2.75;
		return pricePerKm * local.getDistance();
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
