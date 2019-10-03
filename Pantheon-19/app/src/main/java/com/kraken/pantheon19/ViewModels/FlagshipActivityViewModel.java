package com.kraken.pantheon19.ViewModels;
/*
 * created by RahulKraken on 25-08-2019 at 15:57.
 */

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Repositories.EventRepository;

import java.util.List;

public class FlagshipActivityViewModel extends AndroidViewModel {
    private static final String TAG = "FlagshipActivityViewMod";

    private EventRepository eventRepository;
    private LiveData<List<Event>> eventList;

    public FlagshipActivityViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        eventList = eventRepository.getFlagshipEvents();
    }

    public LiveData<List<Event>> getEventList() {
        return eventList;
    }

    public void insert(Event event) {
        eventRepository.insertEvent(event);
    }

    public void update(Event event) {
        eventRepository.updateEvent(event);
    }

    public void delete(Event event) {
        eventRepository.deleteEvent(event);
    }

    public void deleteAll() {
        eventRepository.deleteAllEvents();
    }

    public LiveData<Event> getEvent(int eventId) {
        return eventRepository.getEvent(eventId);
    }
}
