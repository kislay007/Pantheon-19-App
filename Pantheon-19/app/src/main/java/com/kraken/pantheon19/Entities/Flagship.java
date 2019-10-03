package com.kraken.pantheon19.Entities;
/*
 * created by RahulKraken on 11-09-2019 at 07:05.
 */

import java.io.Serializable;

public class Flagship implements Serializable {

    private int title, desc, rules, venue, time, day, coordinators, colorId, imageId;

    public Flagship(int title, int desc, int rules, int venue, int time, int day, int coordinators, int colorId, int imageId) {
        this.title = title;
        this.desc = desc;
        this.rules = rules;
        this.venue = venue;
        this.time = time;
        this.day = day;
        this.coordinators = coordinators;
        this.colorId = colorId;
        this.imageId = imageId;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDesc() {
        return desc;
    }

    public void setDesc(int desc) {
        this.desc = desc;
    }

    public int getRules() {
        return rules;
    }

    public void setRules(int rules) {
        this.rules = rules;
    }

    public int getVenue() {
        return venue;
    }

    public void setVenue(int venue) {
        this.venue = venue;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getCoordinators() {
        return coordinators;
    }

    public void setCoordinators(int coordinators) {
        this.coordinators = coordinators;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Flagship{" +
                "colorId=" + colorId +
                ", imageId=" + imageId +
                ", title=" + title +
                ", desc=" + desc +
                ", rules=" + rules +
                ", venue=" + venue +
                ", time=" + time +
                ", day=" + day +
                ", coordinators=" + coordinators +
                '}';
    }
}
