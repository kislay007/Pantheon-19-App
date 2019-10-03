package com.kraken.pantheon19.Repositories;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.kraken.pantheon19.Dao.EventDao;
import com.kraken.pantheon19.Dao.WinnerDao;
import com.kraken.pantheon19.Database.EventDatabase;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Entities.Winner;

import java.util.List;

public class EventRepository {
    private static final String TAG = "EventRepository";

    private static LiveData<List<Event>> allEvents;
    private static LiveData<List<Event>> allFlagshipEvents;
    private static LiveData<List<Event>> events;
    private static LiveData<Event> event;
    private static LiveData<Winner> winner;
    private EventDao eventDao;
    private WinnerDao winnerDao;

    public EventRepository(Context applicationContext) {
        EventDatabase eventDatabase = EventDatabase.getInstance(applicationContext);
        eventDao = eventDatabase.eventDao();
        winnerDao = eventDatabase.winnerDao();

//        event = eventDao.getEvent(1);
        allEvents = eventDao.getAllEvents();
        allFlagshipEvents = eventDao.getFlagshipEvents();
        events = eventDao.getEventForDay("%1%");
    }

    // eventDao methods
    public void insertEvent(Event event) {
        new InsertEventAsyncTask(eventDao).execute(event);
    }

    public void updateEvent(Event event) {
        new UpdateEventAsyncTask(eventDao).execute(event);
    }

    public void deleteEvent(Event event) {
        new DeleteEventAsyncTask(eventDao).execute(event);
    }

    public void deleteAllEvents() {
        new DeleteAllEventAsyncTask(eventDao).execute();
    }

    public LiveData<Event> getEvent(int eventId) {
        new GetEventAsyncTask(eventDao).execute(eventId);
        return event;
    }

    public LiveData<List<Event>> getAllEvents() {
//        new GetAllEventAsyncTask(eventDao).execute();
        Log.d(TAG, "getAllEvents: " + allEvents.getValue());
        return allEvents;
    }

    public LiveData<List<Event>> getFlagshipEvents() {
        Log.d(TAG, "getFlagshipEvents: " + allFlagshipEvents.getValue());
        return allFlagshipEvents;
    }

    public LiveData<List<Event>> getEventsForDay(String day) {
        Log.d(TAG, "getEventsForDay: " + events.getValue());
        return eventDao.getEventForDay(day);
    }

    // winnerDao methods
    public void insertWinner(Winner winner) {
        new InsertWinnerAsyncTask(winnerDao).execute(winner);
    }

    public void updateWinner(Winner winner) {
        new UpdateWinnerAsyncTask(winnerDao).execute(winner);
    }

    public void deleteWinner(Winner winner) {
        new DeleteWinnerAsyncTask(winnerDao).execute(winner);
    }

    public void deleteAllWinners() {
        new DeleteAllWinnerAsyncTask(winnerDao).execute();
    }

    public LiveData<Winner> getWinner(int eventId) {
        new GetWinnerAsyncTask(winnerDao).execute(eventId);
        return winner;
    }

    /*
    Async tasks for event table transactions
     */
    private static class InsertEventAsyncTask extends AsyncTask<Event, Void, Void> {
        EventDao eventDao;

        InsertEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.insert(events[0]);
            return null;
        }
    }

    private static class UpdateEventAsyncTask extends AsyncTask<Event, Void, Void> {
        EventDao eventDao;

        UpdateEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.update(events[0]);
            return null;
        }
    }

    private static class DeleteEventAsyncTask extends AsyncTask<Event, Void, Void> {
        EventDao eventDao;

        DeleteEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Event... events) {
            eventDao.delete(events[0]);
            return null;
        }
    }

    private static class DeleteAllEventAsyncTask extends AsyncTask<Void, Void, Void> {
        EventDao eventDao;

        DeleteAllEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.deleteAll();
            return null;
        }
    }

    private static class GetAllEventAsyncTask extends AsyncTask<Void, Void, Void> {
        EventDao eventDao;

        GetAllEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            allEvents = eventDao.getAllEvents();
            return null;
        }
    }

    private static class GetEventAsyncTask extends AsyncTask<Integer, Void, Void> {
        EventDao eventDao;

        GetEventAsyncTask(EventDao eventDao) {
            this.eventDao = eventDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            event = eventDao.getEvent(integers[0]);
            return null;
        }
    }

    /*
    Async tasks for winner table transactions
     */
    private static class InsertWinnerAsyncTask extends AsyncTask<Winner, Void, Void> {
        WinnerDao winnerDao;

        InsertWinnerAsyncTask(WinnerDao winnerDao) {
            this.winnerDao = winnerDao;
        }

        @Override
        protected Void doInBackground(Winner... winners) {
            winnerDao.insert(winners[0]);
            return null;
        }
    }

    private static class UpdateWinnerAsyncTask extends AsyncTask<Winner, Void, Void> {
        WinnerDao winnerDao;

        UpdateWinnerAsyncTask(WinnerDao winnerDao) {
            this.winnerDao = winnerDao;
        }

        @Override
        protected Void doInBackground(Winner... winners) {
            winnerDao.update(winners[0]);
            return null;
        }
    }

    private static class DeleteWinnerAsyncTask extends AsyncTask<Winner, Void, Void> {
        WinnerDao winnerDao;

        DeleteWinnerAsyncTask(WinnerDao winnerDao) {
            this.winnerDao = winnerDao;
        }

        @Override
        protected Void doInBackground(Winner... winners) {
            winnerDao.delete(winners[0]);
            return null;
        }
    }

    private static class DeleteAllWinnerAsyncTask extends AsyncTask<Void, Void, Void> {
        WinnerDao winnerDao;

        DeleteAllWinnerAsyncTask(WinnerDao winnerDao) {
            this.winnerDao = winnerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            winnerDao.deleteAllWinners();
            return null;
        }
    }

    private static class GetWinnerAsyncTask extends AsyncTask<Integer, Void, Void> {
        WinnerDao winnerDao;

        GetWinnerAsyncTask(WinnerDao winnerDao) {
            this.winnerDao = winnerDao;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            winner = winnerDao.getWinner(integers[0]);
            return null;
        }
    }
}
