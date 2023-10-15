package com.m2dfs.MatchService.domain;

public class Match {
    private int id;
    private String location;
    private int team1Id;
    private int team2Id;

    public Match(int id, String location, int team1Id, int team2Id) {
        super();
        this.id = id;
        this.location = location;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() { return location; }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTeam1Id() {
        return team1Id;
    }

    public void setTeam1Id(int team1Id) {
        this.team1Id = team1Id;
    }

    public int getTeam2Id() {
        return team2Id;
    }

    public void setTeam2Id(int team2Id) {
        this.team2Id = team2Id;
    }
}

