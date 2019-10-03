package com.kraken.pantheon19.Entities;

public class Sponsor {
    private String sponsorName;
    private int sponsorImageId;

    public Sponsor(String sponsorName,int sponsorImageId) {
        this.sponsorImageId=sponsorImageId;
        this.sponsorName=sponsorName;
    }

    public String getSponsorName() {
        return sponsorName;
    }

    public void setSponsorName(String name) {
        this.sponsorName = name;
    }

    public int getSponsorImage() {
        return sponsorImageId;
    }

    public void setSponsorImage(int id) {
        this.sponsorImageId = id;
    }
}
