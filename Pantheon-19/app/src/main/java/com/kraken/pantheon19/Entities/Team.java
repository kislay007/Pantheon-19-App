package com.kraken.pantheon19.Entities;
/*
 * created by RahulKraken on 08-09-2019 at 20:00.
 */

public class Team {
    private int teamId;
    private String teamName;
    private int teamPos;
    private int points;

    public Team(int teamId, String teamName, int teamPos, int points) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamPos = teamPos;
        this.points = points;
    }

    public int getTeamId() { return teamId; }

    public String getTeamName() { return teamName; }

    public int getTeamPos() { return teamPos; }

    public void setTeamPos(int teamPos) {
        this.teamPos = teamPos;
    }

    public int getPoints() { return points; }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", teamName='" + teamName + '\'' +
                ", teamPos=" + teamPos +
                ", points=" + points +
                '}';
    }
}
