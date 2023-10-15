package com.m2dfs.MatchService.controller;

import com.m2dfs.MatchService.domain.Match;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/matches")
public class MatchController {

    private static Map<Integer, Match> matchDB = new HashMap<>();

    static {
        Match match1 = new Match(1, "Bastia", 1, 2);
        Match match2 = new Match(2, "Aiacciu", 2, 1);

        matchDB.put(match1.getId(), match1);
        matchDB.put(match2.getId(), match2);
    }

    @GetMapping("/{id}")
    public Match getMatchById(@PathVariable int id) {
        return matchDB.get(id);
    }

    @PostMapping
    public Match addMatch(@RequestBody Match match) {
        matchDB.put(match.getId(), match);
        return match;
    }

    @PutMapping("/{id}")
    public Match updateMatch(@PathVariable int id, @RequestBody Match match) {
        match.setId(id);
        matchDB.put(id, match);
        return match;
    }

    @DeleteMapping("/{id}")
    public void deleteMatch(@PathVariable int id) {
        matchDB.remove(id);
    }

    @GetMapping("/matches-count/{teamId}")
    public int getMatchesCountForTeam(@PathVariable int teamId) {
        // Assuming matchDB contains the match data
        long matchesCount = matchDB.stream()
                .filter(match -> match.getTeam1Id() == teamId || match.getTeam2Id() == teamId)
                .count();
        return (int) matchesCount;
    }
}
