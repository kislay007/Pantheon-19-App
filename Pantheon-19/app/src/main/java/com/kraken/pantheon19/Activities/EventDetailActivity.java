package com.kraken.pantheon19.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Entities.Flagship;
import com.kraken.pantheon19.Entities.SharedThemePref;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.Constants;
import com.kraken.pantheon19.Utils.Services;
import com.kraken.pantheon19.Utils.StringUtils;

import java.util.List;

public class EventDetailActivity extends AppCompatActivity {
    private static final String TAG = "EventDetailActivity";

    private Event event;
    private Flagship flagship;
    private TextView scoreOne, scoreTwo, scoreThree, venue, time, desc, rulesLabel, rulesDetail,contactlabel,contactDetail;
    private ImageView imageView;
    private LinearLayout contactLinearLayout;
    ConstraintLayout eventDetailLayout;
    CoordinatorLayout eventDetailLayoutTwo;
    SharedThemePref sharedThemePref;
    boolean isDark;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        // add app bar
        eventDetailLayout=findViewById(R.id.event_detail_layout);
        eventDetailLayoutTwo=findViewById(R.id.event_detail_layout_two);
        toolbar = findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Event title");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        scoreOne = findViewById(R.id.tv_score_one);
        scoreTwo = findViewById(R.id.tv_score_two);
        scoreThree = findViewById(R.id.tv_score_three);

        venue = findViewById(R.id.tv_venue);
        time = findViewById(R.id.tv_time);
        desc = findViewById(R.id.tv_desc);
        imageView = findViewById(R.id.image_main);

        rulesLabel = findViewById(R.id.tv_rules_label);
        rulesDetail = findViewById(R.id.tv_rules_detail);
        contactlabel=findViewById(R.id.tv_contact);

        contactLinearLayout = findViewById(R.id.contacts_linear_layout);

        // get intent
        event = (Event) getIntent().getSerializableExtra(getResources().getString(R.string.event_intent_pass_key));
        Log.d(TAG, "onCreate: " + event);
        flagship = (Flagship) getIntent().getSerializableExtra(getResources().getString(R.string.flagship_intent_pass_key));
        Log.d(TAG, "onCreate: " + flagship);

        sharedThemePref=new SharedThemePref();

        //set theme
        isDark = sharedThemePref.getThemeStatePref(this);
        if(isDark) setDarkTheme();
        else setLightTheme();

        setupViews();


    }

    @Override
    public boolean onSupportNavigateUp() {
        Log.d(TAG, "onSupportNavigateUp: back pressed");
        finish();
        return true;
    }

    private void setupViews() {
        if (event != null) {
            // app bar title
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(event.getEventName());

            rulesLabel.setVisibility(View.GONE);
            rulesDetail.setVisibility(View.GONE);

            venue.setText(event.getVenue() != null ? event.getVenue() : "-");
            time.setText(event.getTiming() != null ? event.getTiming() : "-");
            desc.setText(event.getDescription() != null ? event.getDescription() : "-");

            imageView.setImageResource(Services.getImgResourceId(this, StringUtils.getImageResourceName(event.getEventName())));

            switch (event.getTag()) {
                case "informal":
                    setPoints(Constants.INFORMAL_POINTS);
                    break;
                case "formal":
                    setPoints(Constants.FORMAL_POINTS);
                    break;
                case "flagship":
                    setPoints(Constants.FLAGSHIP_POINTS);
                    break;
            }

            addContactInfo(event.getCoordinators());
        } else if (flagship != null) {
            // app bar title
            if (getSupportActionBar() != null) getSupportActionBar().setTitle(flagship.getTitle());

            rulesLabel.setVisibility(View.VISIBLE);
            rulesDetail.setVisibility(View.VISIBLE);

            rulesDetail.setText(getResources().getString(flagship.getRules()));

            venue.setText(flagship.getVenue());
            time.setText(flagship.getTime());
            desc.setText(flagship.getDesc());

            imageView.setImageResource(flagship.getImageId());

            setPoints(Constants.FLAGSHIP_POINTS);

            addContactInfo(getResources().getString(flagship.getCoordinators()));
        }
    }

    private void addContactInfo(String s) {
        isDark=sharedThemePref.getThemeStatePref(EventDetailActivity.this);
        List<String> contacts = StringUtils.parseContact(s, "\\,");
        for (String contact : contacts) {
            contactDetail = (TextView) getLayoutInflater().inflate(R.layout.coordinator_item_row, null);
            contactDetail.setText(contact.trim());
            if(isDark) contactDetail.setTextColor(getResources().getColor(R.color.md_white_1000));
            else contactDetail.setTextColor(getResources().getColor(R.color.md_black_1000));
            contactLinearLayout.addView(contactDetail);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0,0,0,96);
        contactDetail.setLayoutParams(params);
    }

    private void setPoints(int[] points) {
        scoreOne.setText(String.valueOf(points[0]));
        scoreTwo.setText(String.valueOf(points[1]));
        scoreThree.setText(String.valueOf(points[2]));
    }

    public void setDarkTheme() {
        //set background
        eventDetailLayout.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        eventDetailLayoutTwo.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        //set scores color
        scoreOne.setTextColor(getResources().getColor(R.color.md_white_1000));
        scoreTwo.setTextColor(getResources().getColor(R.color.md_white_1000));
        scoreThree.setTextColor(getResources().getColor(R.color.md_white_1000));
        //set labels color
        venue.setTextColor(getResources().getColor(R.color.md_yellow_500));
        time.setTextColor(getResources().getColor(R.color.md_yellow_500));
        rulesLabel.setTextColor(getResources().getColor(R.color.md_yellow_500));
        contactlabel.setTextColor(getResources().getColor(R.color.md_yellow_500));
        //set details color
        desc.setTextColor(getResources().getColor(R.color.md_white_1000));
        rulesDetail.setTextColor(getResources().getColor(R.color.md_white_1000));
        //set toolbar theme
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_white_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_white_1000), PorterDuff.Mode.SRC_ATOP);
        //set status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_black_1000));
        View decorView = getWindow().getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  ligh
    }

    public void setLightTheme() {
        //set background
        eventDetailLayout.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        eventDetailLayoutTwo.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        //set scores color
        scoreOne.setTextColor(getResources().getColor(R.color.md_black_1000));
        scoreTwo.setTextColor(getResources().getColor(R.color.md_black_1000));
        scoreThree.setTextColor(getResources().getColor(R.color.md_black_1000));
        //set labels color
        venue.setTextColor(getResources().getColor(R.color.md_blue_700));
        time.setTextColor(getResources().getColor(R.color.md_blue_700));
        rulesLabel.setTextColor(getResources().getColor(R.color.md_blue_700));
        contactlabel.setTextColor(getResources().getColor(R.color.md_blue_700));
        //set details color
        desc.setTextColor(getResources().getColor(R.color.md_black_1000));
        rulesDetail.setTextColor(getResources().getColor(R.color.md_black_1000));
        //set toolbar theme
        toolbar.setTitleTextColor(getResources().getColor(R.color.md_black_1000));
        toolbar.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.md_black_1000), PorterDuff.Mode.SRC_ATOP);
        //set status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.md_white_1000));// set status background white
    }
}
