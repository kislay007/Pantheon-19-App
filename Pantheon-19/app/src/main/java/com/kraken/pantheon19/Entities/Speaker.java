package com.kraken.pantheon19.Entities;
/*
 * created by RahulKraken on 08-09-2019 at 21:14.
 */

public class Speaker {

    private String name, detail, time, venue, day;
    private int imageId;

    public Speaker(String name, String detail, String time, String venue, String day, int imageId) {
        this.name = name;
        this.detail = detail;
        this.time = time;
        this.venue = venue;
        this.day=day;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() { return detail; }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String time) {
        this.day = day;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
