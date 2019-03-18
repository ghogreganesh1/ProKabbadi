package com.Ganesh.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.Ganesh.dao.DefaultMatchScheduler;
import com.Ganesh.model.Match;
import com.Ganesh.model.Team;


@RestController
public class usercontroller {

	

	List<Team> teamList = new ArrayList<Team>();

	@PostMapping("/Register_Pro-Kabbadi_team")
	public int getTeams(@RequestHeader List<Team> team) {
		Iterator itr = team.iterator();
		while (itr.hasNext()) {
			teamList.add((Team) itr.next());
		}
		return HttpServletResponse.SC_OK;
	}

	@GetMapping("/getMatchScheduler")
	public List<Match> getMatchScheduler() {
		List<Match> matches = new DefaultMatchScheduler().generate(teamList, new Date());
		return matches;
	}
}
