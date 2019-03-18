package com.Ganesh;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import com.Ganesh.dao.DefaultMatchScheduler;
import com.Ganesh.dao.DefaultTeamDao;
import com.Ganesh.dao.TeamRepository;
import com.Ganesh.model.Match;
import com.Ganesh.model.Team;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class Application implements CommandLineRunner {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		TeamRepository teamRepo = new DefaultTeamDao();

		List<Team> teamList = new ArrayList<Team>();
		teamList.add(teamRepo.fetch(1));
		teamList.add(teamRepo.fetch(2));
		teamList.add(teamRepo.fetch(3));
		teamList.add(teamRepo.fetch(4));

		List<Match> matches = new DefaultMatchScheduler().generate(teamList, new Date());

		Collections.sort(matches, new Comparator<Match>() {
			public int compare(Match m1, Match m2) {
				return m1.getDate().compareTo(m2.getDate());
			}
		});

		System.out.println(
				" --------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(
				formatString("TEAM-A") + formatString("TEAM-B") + formatString("Location") + formatString("Date"));
		System.out.println(
				" --------------------------------------------------------------------------------------------------------------------------------------------");
		for (Match match : matches) {
			System.out.println(formatString(match.getTeamA().getName()) + formatString(match.getTeamB().getName())
					+ formatString(match.getLocation()) + formatString(formatDate(match.getDate())));
		}
		System.out.println(
				" --------------------------------------------------------------------------------------------------------------------------------------------");

	}

	private static String formatString(String actualString) {
		return StringUtils.rightPad(actualString, 20, "");
	}

	private static String formatDate(Date actualDate) {
		return new SimpleDateFormat("dd-MM-yyyy").format(actualDate);
	}

}