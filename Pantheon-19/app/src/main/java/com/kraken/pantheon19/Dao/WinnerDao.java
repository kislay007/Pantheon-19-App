package com.kraken.pantheon19.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.kraken.pantheon19.Entities.Winner;

@Dao
public interface WinnerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Winner winner);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Winner winner);

    @Delete
    void delete(Winner winner);

    @Query("DELETE FROM WINNERS_TABLE")
    void deleteAllWinners();

    @Query("SELECT * FROM winners_table WHERE event_id = :event_id LIMIT 1")
    LiveData<Winner> getWinner(int event_id);
}
