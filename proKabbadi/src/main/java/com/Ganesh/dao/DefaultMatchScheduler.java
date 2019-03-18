package com.Ganesh.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.Ganesh.model.Match;
import com.Ganesh.model.Team;

public class DefaultMatchScheduler {

	private List<Match> initializeMatches(List<Team> teams) {
		// Define matches to be return
		List<Match> matches = new ArrayList<Match>();

		// Initialize matches
		for (Team teamA : teams) {
			for (Team teamB : teams) {
				if (teamA.getId() != teamB.getId()) {
					Match match = new Match(teamA, teamB);
					match.setLocation(teamA.getCity());

					matches.add(match);
				}
			}
		}

		// Return list of matches
		return matches;
	}

	private boolean isMatchScheduleRemaining(List<Match> matches) {
		for (Match match : matches) {
			if (match.getDate() == null) {
				return true;
			}
		}
		return false;
	}

	private List<Integer> prepareParticipatedTeamList(List<Match> matches, Date date) {
		List<Integer> teamIds = new ArrayList<Integer>();
		for (Match match : matches) {
			if (match.getDate() != null) {
				if (date.equals(match.getDate())) {
					teamIds.add(match.getTeamA().getId());
					teamIds.add(match.getTeamB().getId());
				}
			}
		}
		return teamIds;
	}

	public List<Match> generate(List<Team> teams, Date startDate) {
		// Generate matches from combination of teams
		List<Match> matches = initializeMatches(teams);

		// Define current date
		DateTime currentDate = new DateTime(startDate.getTime());

		// Loop till one of the match date has not been scheduled
		while (isMatchScheduleRemaining(matches)) {

			// Prepare already participated team id list
			List<Integer> participatedTeamIds = prepareParticipatedTeamList(matches,
					currentDate.minus(Period.days(1)).toDate());

			// Swap object in list
			Random random = new Random();
			Collections.swap(matches, random.nextInt(matches.size()), random.nextInt(matches.size()));

			for (Match match : matches) {
				if (match.getDate() == null && !participatedTeamIds.contains(match.getTeamA().getId())
						&& !participatedTeamIds.contains(match.getTeamB().getId())) {
					match.setDate(currentDate.toDate());
					participatedTeamIds.add(match.getTeamA().getId());
					participatedTeamIds.add(match.getTeamB().getId());
				}
			}

			// Increment current date by 2 days
			currentDate = currentDate.plus(Period.days(1));
		}

		return matches;
	}

}
