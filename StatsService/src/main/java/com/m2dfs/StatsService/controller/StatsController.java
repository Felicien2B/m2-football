package com.m2dfs.StatsService.controller;

import com.m2dfs.StatsService.domain.PlayerStats;
import com.m2dfs.StatsService.domain.TeamStats;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/stats")
public class StatsController {

        @Autowired
        private RestTemplate restTemplate;

    @ApiOperation(value = "Get player info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
        @GetMapping("/player/{playerId}")
        public String getPlayer(@PathVariable int playerId) {
            System.out.println("Getting player details for " + playerId);

            String response = restTemplate.exchange(
                    "http://playerservice/players/{playerId}",
                    HttpMethod.GET, null, new ParameterizedTypeReference<String>() {
                    }, playerId).getBody();

            System.out.println("Response Body " + response);

            return "Player Id -  " + playerId + " [ Player Details " + response + " ]";
        }

        public String fallbackMethod(int playerId) {
            return "Fallback response: No player details available temporarily";
        }

/*
@ApiOperation(value = "Get team stats: matches played and wins count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/team-stats/{teamId}")
    public TeamStats getTeamStats(@PathVariable int teamId) {
        List<Match> teamMatches = matchDB.stream()
                .filter(match -> match.getTeam1Id() == teamId || match.getTeam2Id() == teamId)
                .collect(Collectors.toList());

        return calculateTeamStats(teamId, teamMatches);
    }

@ApiOperation(value = "Get player stats: matches played and wins count")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/player-stats/{playerId}")
    public PlayerStats getPlayerStats(@PathVariable int playerId) {
        // Find the team to which the player belongs based on playerId
        int teamId = findTeamByPlayerId(playerId);
        if (teamId == -1) {
            // Player not found in any team
            return null;
        }

        // Get the matches for the team
        List<Match> teamMatches = matchDB.stream()
                .filter(match -> match.getTeam1Id() == teamId || match.getTeam2Id() == teamId)
                .collect(Collectors.toList());

        return calculatePlayerStats(playerId, teamMatches);
    }

    private TeamStats calculateTeamStats(int teamId, List<Match> matches) {
        int totalMatchesPlayed = matches.size();
        int totalWins = 0;

        for (Match match : matches) {
            int winningTeamId = match.getTeam1Score() > match.getTeam2Score() ? match.getTeam1Id() : match.getTeam2Id();
            if (winningTeamId == teamId) {
                totalWins++;
            }
        }

        TeamStats teamStats = new TeamStats();
        teamStats.setTeamId(teamId);
        teamStats.setTotalMatchesPlayed(totalMatchesPlayed);
        teamStats.setTotalWins(totalWins);

        return teamStats;
    }

    private PlayerStats calculatePlayerStats(int playerId, List<Match> matches) {
        int totalMatchesPlayed = matches.size();
        int totalWins = 0;

        for (Match match : matches) {
            int winningTeamId = match.getTeam1Score() > match.getTeam2Score() ? match.getTeam1Id() : match.getTeam2Id();
            List<Integer> teamPlayerIds = teamDB.stream()
                    .filter(team -> team.getId() == winningTeamId)
                    .map(Team::getPlayerIds)
                    .findFirst()
                    .orElse(new ArrayList<>());

            if (teamPlayerIds.contains(playerId)) {
                totalWins++;
            }
        }

        PlayerStats playerStats = new PlayerStats();
        playerStats.setPlayerId(playerId);
        playerStats.setTotalMatchesPlayed(totalMatchesPlayed);
        playerStats.setTotalWins(totalWins);

        return playerStats;
    }

    private int findTeamByPlayerId(int playerId) {
        for (Team team : teamDB) {
            if (team.getPlayerIds().contains(playerId)) {
                return team.getId();
            }
        }
        return -1; // Player not found in any team
    }
*/
}
