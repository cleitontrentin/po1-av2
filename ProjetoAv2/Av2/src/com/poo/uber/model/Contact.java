package com.poo.uber.model;

import java.util.List;

public class Contact {

	private String email;
	private List<Phone> phones;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Phone> getPhones() {
		return phones;
	}

	public void addPhone(Phone phone) {
		phones.add(phone);
	}

}
