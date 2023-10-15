package com.m2dfs.MatchService.controller;

import com.m2dfs.MatchService.domain.Match;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/matches")
@EnableCircuitBreaker
@EnableHystrixDashboard
public class MatchController {

    private static Map<Integer, Match> matchDB = new HashMap<>();

    static {
        Match match1 = new Match(1, "Bastia", 1, 2);
        Match match2 = new Match(2, "Aiacciu", 2, 1);

        matchDB.put(match1.getId(), match1);
        matchDB.put(match2.getId(), match2);
    }

    @ApiOperation(value = "Get match info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable int id) {
        return matchDB.get(id);
    }

    @ApiOperation(value = "Add match details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @PostMapping
    public Match addMatch(@RequestBody Match match) {
        matchDB.put(match.getId(), match);
        return match;
    }

    @ApiOperation(value = "Update match details")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable int id, @RequestBody Match match) {
        match.setId(id);
        matchDB.put(id, match);
        return match;
    }

    @ApiOperation(value = "Delete match")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchDB.remove(id);
    }

    /*
    @ApiOperation(value = "Get number of matches played by a given team")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/matches-count/{teamId}")
    public int getMatchesCountForTeam(@PathVariable int teamId) {
        long matchesCount = matchDB.stream()
                .filter(match -> match.getTeam1Id() == teamId || match.getTeam2Id() == teamId)
                .count();
        return (int) matchesCount;
    }
    */
}
