package com.poo.uber.model;

import java.util.List;

public class History {

	private List<Race> races;

	public List<Race> getRaces() {
		return races;
	}

	public void addRace(Race race) {
		races.add(race);
	}

}
