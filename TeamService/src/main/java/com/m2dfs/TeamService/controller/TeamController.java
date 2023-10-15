package com.m2dfs.TeamService.controller;

import com.m2dfs.TeamService.domain.Team;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private static Map<Integer, Team> teamDB = new HashMap<>();

    static {
        Team team1 = new Team(1, "SCB");
        Team team2 = new Team(2, "ACA");

        teamDB.put(team1.getId(), team1);
        teamDB.put(team2.getId(), team2);
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Long id) {
        return teamDB.get(id);
    }

    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        teamDB.put(team.getId(), team);
        return team;
    }

    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable int id, @RequestBody Team team) {
        teamDB.put(id, team);
        return team;
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable int id) {
        teamDB.remove(id);
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return new ArrayList<>(teamDB.values());
    }
}
