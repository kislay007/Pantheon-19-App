package com.kraken.pantheon19.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Adapters.EventsRecyclerViewAdapter;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Repositories.EventRepository;
import com.kraken.pantheon19.ViewModels.EventActivityViewModel;

import java.util.List;

public class DayOneFragment extends Fragment {
    private static final String TAG = "DayOneFragment";
    private RecyclerView recyclerView;
    private EventsRecyclerViewAdapter adapter;
    boolean isDark;

    public DayOneFragment(boolean isDark) {
        this.isDark=isDark;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_day_one, container, false);
        recyclerView = v.findViewById(R.id.day_one_recycler_view);
        initRecyclerView();

        // live data setup
        EventActivityViewModel viewModel = ViewModelProviders.of(this).get(EventActivityViewModel.class);

        // observe event list now
        viewModel.getEventList("%1%").observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                Log.d(TAG, "onChanged: " + events.toString());
                adapter.setEvents(events);
            }
        });
        if(isDark) recyclerView.setBackgroundColor(Color.BLACK);
        else recyclerView.setBackgroundColor(Color.WHITE);
        return v;
    }

    private void initRecyclerView() {
        adapter = new EventsRecyclerViewAdapter(getContext(),isDark);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}