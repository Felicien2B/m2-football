package com.m2dfs.TeamService.domain;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private int id;
    private String name;
    private List<Integer> PlayerIds;

    public Team(int id, String name) {
        super();
        this.id = id;
        this.name = name;
        this.PlayerIds = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getPlayerIds() {
        return PlayerIds;
    }

    public void setPlayerIds(List<Integer> PlayerIds) {
        this.PlayerIds = PlayerIds;
    }
}
