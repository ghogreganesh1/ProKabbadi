package com.Ganesh.model;

import java.util.Date;

public class Match {
	private final Team teamA;
	private final Team teamB;
	private String location;
	private Date date;

	public Match(Team teamA, Team teamB, String location, Date date) {
		super();
		this.teamA = teamA;
		this.teamB = teamB;
		this.location = location;
		this.date = date;
	}

	public Match(Team teamA, Team teamB) {
		super();
		this.teamA = teamA;
		this.teamB = teamB;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date formatDateTime) {
		this.date = formatDateTime;
	}

	public Team getTeamA() {
		return teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

}
