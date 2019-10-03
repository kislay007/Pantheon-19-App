package com.kraken.pantheon19.ViewModels;
/*
 * created by RahulKraken on 09-09-2019 at 11:50.
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Repositories.EventRepository;

import java.util.List;

public class EventActivityViewModel extends AndroidViewModel {
    private static final String TAG = "EventActivityViewModel";

    private EventRepository eventRepository;
    private LiveData<List<Event>> eventList;

    public EventActivityViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        eventList = eventRepository.getEventsForDay("%1%");
    }

    public LiveData<List<Event>> getEventList(String day) {
        eventList = eventRepository.getEventsForDay(day);
        return eventList;
    }
}
