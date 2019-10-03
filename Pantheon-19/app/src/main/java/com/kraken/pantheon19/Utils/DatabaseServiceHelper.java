package com.kraken.pantheon19.Utils;
/*
 * created by RahulKraken on 09-09-2019 at 16:18.
 */

import android.content.Context;
import android.util.Log;

import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.MyApplication;
import com.kraken.pantheon19.Repositories.EventRepository;

import java.util.List;

public class DatabaseServiceHelper {
    private static final String TAG = "DatabaseServiceHelper";

    public static void addToEventDb(List<Event> events) {
        EventRepository repository = new EventRepository(MyApplication.getContext());
        Log.d(TAG, "addToEventDb: Hey dbService working");

        for (Event e : events) {
            Log.d(TAG, "addToEventDb: inserting: " + e);
            repository.insertEvent(e);
        }
    }
}
