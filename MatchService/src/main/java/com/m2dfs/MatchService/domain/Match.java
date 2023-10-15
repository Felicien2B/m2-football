package com.m2dfs.MatchService.domain;

public class Match {
    private int id;
    private String location;
    private int team1Id;
    private int team2Id;
    private int team1Score;
    private int team2Score;

    public Match(int id, String location, int team1Id, int team2Id) {
        super();
        this.id = id;
        this.location = location;
        this.team1Id = team1Id;
        this.team2Id = team2Id;
        this.team1Score = 0;
        this.team2Score = 0;
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

    public int getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(int team1Score) {
        this.team1Score = team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(int team2Score) {
        this.team2Score = team2Score;
    }
}

