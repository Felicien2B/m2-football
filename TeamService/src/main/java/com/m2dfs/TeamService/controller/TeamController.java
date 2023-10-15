package com.m2dfs.TeamService.controller;

import com.m2dfs.TeamService.domain.Team;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teams")
@EnableCircuitBreaker
@EnableHystrixDashboard
public class TeamController {

    private static Map<Integer, Team> teamDB = new HashMap<>();

    static {
        Team team1 = new Team(1, "SCB");
        Team team2 = new Team(2, "ACA");

        teamDB.put(team1.getId(), team1);
        teamDB.put(team2.getId(), team2);
    }

    @ApiOperation(value = "Get team info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable int id) {
        return teamDB.get(id);
    }

    @ApiOperation(value = "Add team info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @PostMapping
    public Team addTeam(@RequestBody Team team) {
        teamDB.put(team.getId(), team);
        return team;
    }

    @ApiOperation(value = "Update team info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable int id, @RequestBody Team team) {
        teamDB.put(id, team);
        return team;
    }

    @ApiOperation(value = "Delete team")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable int id) {
        teamDB.remove(id);
    }

    @ApiOperation(value = "Get list of all teams")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping
    public List<Team> getAllTeams() {
        return new ArrayList<>(teamDB.values());
    }
}
