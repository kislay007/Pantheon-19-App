package com.kraken.pantheon19.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "winners_table",
        foreignKeys = @ForeignKey(entity = Event.class, parentColumns = "id", childColumns = "event_id", onDelete = ForeignKey.CASCADE))
public class Winner {

    @PrimaryKey
    @ColumnInfo(name = "event_id")
    private int eventId;

    private String first;
    private String second;
    private String third;

    public Winner(int eventId, String first, String second, String third) {
        this.eventId = eventId;
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public int getEventId() {
        return eventId;
    }

    public String getFirst() {
        return first;
    }

    public String getSecond() {
        return second;
    }

    public String getThird() {
        return third;
    }
}
