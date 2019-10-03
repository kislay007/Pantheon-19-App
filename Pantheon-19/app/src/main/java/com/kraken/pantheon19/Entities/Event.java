package com.kraken.pantheon19.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "event_table")
public class Event implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    private String eventName;
    private String description;
    private String status;
    private String timing;
    private String venue;
    private String day;

    @ColumnInfo(name = "team_size")
    private String teamSize;

    private String coordinators;
    private String tag;
    private String duration;

    @Ignore
    public Event() {}

    public Event(int id, String eventName, String description, String status, String timing, String venue, String day, String teamSize, String coordinators, String tag, String duration) {
        this.id = id;
        this.eventName = eventName;
        this.description = description;
        this.status = status;
        this.timing = timing;
        this.venue = venue;
        this.day = day;
        this.teamSize = teamSize;
        this.coordinators = coordinators;
        this.tag = tag;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getTiming() {
        return timing;
    }

    public String getVenue() {
        return venue;
    }

    public String getDay() {
        return day;
    }

    public String getTeamSize() {
        return teamSize;
    }

    public String getCoordinators() {
        return coordinators;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTeamSize(String teamSize) {
        this.teamSize = teamSize;
    }

    public void setCoordinators(String coordinators) {
        this.coordinators = coordinators;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + eventName + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", time='" + timing + '\'' +
                ", venue='" + venue + '\'' +
                ", day=" + day +
                ", teamSize=" + teamSize +
                ", coordinators='" + coordinators + '\'' +
                ", tag='" + tag + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
