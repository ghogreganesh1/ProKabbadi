package com.Ganesh.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.Ganesh.model.Team;

public class DefaultTeamDao implements TeamRepository {
	private Map<Integer, Team> teamMap = new ConcurrentHashMap<Integer, Team>();

	public DefaultTeamDao() {
		initializeTeam();
	}
	
	//this input is for sample project ...for dynamic input, restcontroller will come into picture
	private void initializeTeam() {
		teamMap.put(1, prepareTeam(1, "Pune - Warrior", "Pune"));
		teamMap.put(2, prepareTeam(2, "Mumbai - Indians", "Mumbai"));
		teamMap.put(3, prepareTeam(3, "Delhi - Devill", "Delhi"));
		teamMap.put(4, prepareTeam(4, "Chandigadh - King", "Chandigadh"));

	}

	private Team prepareTeam(int id, String name, String city) {
		Team team = new Team(id, name, city);
		return team;
	}

	@Override
	public Team fetch(int id) {
		return teamMap.get(id);
	}

}
