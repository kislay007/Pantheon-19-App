package com.kraken.pantheon19.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kraken.pantheon19.Entities.Event;

import java.util.List;

@Dao
public interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Event event);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM event_table")
    void deleteAll();

    @Query("SELECT * FROM event_table")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM EVENT_TABLE WHERE id = :eventId LIMIT 1")
    LiveData<Event> getEvent(int eventId);

    @Query("SELECT * FROM EVENT_TABLE WHERE tag = 'flagship'")
    LiveData<List<Event>> getFlagshipEvents();

    @Query("SELECT * FROM event_table WHERE day LIKE :day")
    LiveData<List<Event>> getEventForDay(String day);
}
