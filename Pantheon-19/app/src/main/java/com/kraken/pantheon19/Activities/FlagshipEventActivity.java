package com.kraken.pantheon19.Activities;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.kraken.pantheon19.Adapters.FlagshipRecyclerViewAdapter;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Entities.SharedThemePref;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Repositories.EventRepository;
import com.kraken.pantheon19.ViewModels.FlagshipActivityViewModel;

import java.util.ArrayList;
import java.util.List;

public class FlagshipEventActivity extends AppCompatActivity {
    private static final String TAG = "FlagshipEventActivity";
    private RecyclerView recyclerView;
    private FlagshipActivityViewModel flagshipActivityViewModel;
    private FlagshipRecyclerViewAdapter adapter;
    SharedThemePref sharedThemePref;
    ConstraintLayout flagLayout;
    Toolbar toolbar;
    boolean isDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flagship_event);

        // TODO : app bar
        flagLayout=findViewById(R.id.flagship_list_layout);
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.flagship_activity_title));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        sharedThemePref=new SharedThemePref();

        //set theme
        isDark = sharedThemePref.getThemeStatePref(this);
        if(isDark) setDarkTheme();
        else setLightTheme();

        // setup view model
        flagshipActivityViewModel = ViewModelProviders.of(this).get(FlagshipActivityViewModel.class);

        // observe event list now
        flagshipActivityViewModel.getEventList().observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(List<Event> events) {
                Log.d(TAG, "onChanged: data changed");
//                adapter.setEventList(events);

                for (Event e : events) {
                    Log.d(TAG, "onChanged: " + e);
                }
            }
        });

        recyclerView = findViewById(R.id.flagship_recycler_view);

        setupRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        adapter = new FlagshipRecyclerViewAdapter(this);

        // set on recycler view
        Log.d(TAG, "setupRecyclerView: inflating recycler view");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // snap behavior for recycler view
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    public void setDarkTheme() {
        flagLayout.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        //set status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_black_1000));
        View decorView = getWindow().getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  ligh
    }

    public void setLightTheme() {
        flagLayout.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_ATOP);
        //set status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.md_white_1000));// set status background white
    }
}
