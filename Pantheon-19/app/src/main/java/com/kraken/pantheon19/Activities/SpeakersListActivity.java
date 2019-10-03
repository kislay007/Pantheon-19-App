package com.kraken.pantheon19.Activities;

import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Adapters.SpeakersRecyclerViewAdapter;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Entities.SharedThemePref;
import com.kraken.pantheon19.Entities.Speaker;
import com.kraken.pantheon19.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpeakersListActivity extends AppCompatActivity {
    private static final String TAG = "SpeakersEventActivity";
    private RecyclerView recyclerView;
    private List<Speaker> speakers;
    SharedThemePref sharedThemePref;
    CoordinatorLayout speakerLayout;
    Toolbar toolbar;
    boolean isDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers_list);

        // TODO : app bar
        toolbar = findViewById(R.id.app_bar);
        speakerLayout=findViewById(R.id.speaker_list_layout);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Speakers");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        sharedThemePref=new SharedThemePref();

        //set theme
        isDark = sharedThemePref.getThemeStatePref(this);
        if(isDark) setDarkTheme();
        else setLightTheme();

        recyclerView = findViewById(R.id.speakers_recycler_view);
        setupEventList();
        setupRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp: back pressed");
        finish();
        return true;
    }

    /*
    create list of speakers
    TODO : replace with repository call
     */
    private void setupEventList() {
        speakers = new ArrayList<>();
        speakers.add(new Speaker("Dr.Anandmayee Tej", "A Journey to Stara : Unraveling the Mysteries", "11:00 AM to 12:00 PM",
                "SH-2", "Day 1", R.drawable.anandmayee));
        speakers.add(new Speaker("Dr.Rupashree Purushottam", "Polycystic Ovalian Disease", "2:00 PM to 3:00 PM", "SH-2",
                "Day 1", R.drawable.puru));
        speakers.add(new Speaker("Mr.Soumen Mondal", "Galactic Star Forming", "10:30 AM to 11:30 AM", "SH-2", "Day 2",
                R.drawable.soman));
        speakers.add(new Speaker("Dr.Suresh Kaul Singh", "Menstural Hygiene", "11:30 AM to 12:30 PM", "SH-2", "Day 2",
                R.drawable.suresh));
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        SpeakersRecyclerViewAdapter adapter = new SpeakersRecyclerViewAdapter(this, speakers,isDark);

        // set on recycler view
        Log.d(TAG, "setupRecyclerView: inflating speakers recycler view");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void setDarkTheme() {
        speakerLayout.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_black_1000));

        View decorView = getWindow().getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  ligh
    }

    public void setLightTheme() {
        speakerLayout.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_ATOP);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.md_white_1000));// set status background white
    }
}
