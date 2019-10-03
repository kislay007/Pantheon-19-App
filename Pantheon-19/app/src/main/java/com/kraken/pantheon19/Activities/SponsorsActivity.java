package com.kraken.pantheon19.Activities;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Adapters.SponsorsRecyclerViewAdapter;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Entities.SharedThemePref;
import com.kraken.pantheon19.Entities.Sponsor;
import com.kraken.pantheon19.R;

import java.util.ArrayList;
import java.util.List;

public class SponsorsActivity extends AppCompatActivity {

    private static final String TAG = "SponsorsEventActivity";
    private RecyclerView recyclerView;
    private List<Sponsor> sponsorList;
    SharedThemePref sharedThemePref;
    CoordinatorLayout sponsorLayout;
    Toolbar toolbar;
    boolean isDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        // TODO : app bar
        sponsorLayout=findViewById(R.id.sponsors_list_layout);
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sponsors");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        sharedThemePref=new SharedThemePref();

        //set theme
        isDark = sharedThemePref.getThemeStatePref(this);
        if(isDark) setDarkTheme();
        else setLightTheme();

        recyclerView = findViewById(R.id.sponsors_recycler_view);

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
    create list of events
    TODO : replace with sponsors list
     */
    private void setupEventList() {
        sponsorList = new ArrayList<>();
        sponsorList.add(new Sponsor("",0));
        sponsorList.add(new Sponsor("",R.drawable.emami));
        sponsorList.add(new Sponsor("Co-Sponsor",0));
        sponsorList.add(new Sponsor("",R.drawable.baba_munchies));
        sponsorList.add(new Sponsor("",R.drawable.attitude));
        sponsorList.add(new Sponsor("",R.drawable.kareems));
        sponsorList.add(new Sponsor("Food Partners",0));
        sponsorList.add(new Sponsor("",R.drawable.nescafe));
        sponsorList.add(new Sponsor("Happiness Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.zoomcar));
        sponsorList.add(new Sponsor("Self Car Drive Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.aveta));
        sponsorList.add(new Sponsor("Healthcare Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.b_factory));
        sponsorList.add(new Sponsor("CAT coaching partner",0));
        sponsorList.add(new Sponsor("",R.drawable.zebronics));
        sponsorList.add(new Sponsor("Audio Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.thancos_natural));
        sponsorList.add(new Sponsor("Dessert Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.nirvana));
        sponsorList.add(new Sponsor("Restaurant Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.hackerearth));
        sponsorList.add(new Sponsor("Hosting Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.bigfm));
        sponsorList.add(new Sponsor("Radio Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.niine));
        sponsorList.add(new Sponsor("Sanitary Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.shoppers_mart));
        sponsorList.add(new Sponsor("Apparel Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.codingninjas));
        sponsorList.add(new Sponsor("Coding Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.cora));
        sponsorList.add(new Sponsor("Beauty Partners",0));
        sponsorList.add(new Sponsor("",R.drawable.cocacola));
        sponsorList.add(new Sponsor("Beverage Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.olcademy));
        sponsorList.add(new Sponsor("Student Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.nestle));
        sponsorList.add(new Sponsor("Gifting Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.rollick));
        sponsorList.add(new Sponsor("Ice-cream Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.indigo_paints));
        sponsorList.add(new Sponsor("Creative Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.citygirl));
        sponsorList.add(new Sponsor("Makeup Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.himalaya_opticals));
        sponsorList.add(new Sponsor("Optical Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.knowafest));
        sponsorList.add(new Sponsor("Online Publicity Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.fasttrack));
        sponsorList.add(new Sponsor("Youth Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.hellointern));
        sponsorList.add(new Sponsor("Internship Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.yourstory));
        sponsorList.add(new Sponsor("Digital Media Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.fitnesspro));
        sponsorList.add(new Sponsor("Fitness Partner",0));
        sponsorList.add(new Sponsor("",R.drawable.teqip));
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        SponsorsRecyclerViewAdapter adapter = new SponsorsRecyclerViewAdapter(this, sponsorList,isDark);

        // set on recycler view
        Log.d(TAG, "setupRecyclerView: inflating speakers recycler view");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void setDarkTheme() {
        sponsorLayout.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        //set status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_black_1000));
        View decorView = getWindow().getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  ligh
    }

    public void setLightTheme() {
        sponsorLayout.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_ATOP);
        //set status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.md_white_1000));// set status background white
    }
}
