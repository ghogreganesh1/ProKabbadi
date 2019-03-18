package com.Ganesh.model;

public class Team {
	private int id;
	private String Name;
	private String city;

	public Team() {
	}

	public Team(int id, String name, String city) {
		super();
		this.id = id;
		Name = name;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", Name=" + Name + ", city=" + city + "]";
	}

}
