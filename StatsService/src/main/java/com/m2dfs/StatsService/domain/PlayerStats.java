package com.m2dfs.StatsService.domain;

public class PlayerStats {
    private int id;
    private int totalMatchesPlayed;
    private int totalWins;

    public PlayerStats(int id) {
        super();
        this.id = id;
        this.totalMatchesPlayed = 0;
        this.totalWins = 0;
    }

    public int getId() {
        return id;
    }

    public int getTotalMatchesPlayed() { return totalMatchesPlayed; }

    public int getTotalWins() {
        return totalWins;
    }

}
