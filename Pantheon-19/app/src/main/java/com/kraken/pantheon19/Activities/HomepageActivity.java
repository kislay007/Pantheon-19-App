package com.kraken.pantheon19.Activities;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kraken.pantheon19.Entities.SharedThemePref;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.Constants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HomepageActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "HomepageActivity";
    private CustomTabsIntent customTabsIntent;
    ConstraintLayout constraintLayout,info,flag,event,speaker,lb,sponsor;
    private TextView welcome,des,infotext,flagtext,eventtext,speakertext,lbtext,sponsortext;
    boolean isDark=true;
    private ImageView imageView;
    SharedThemePref sharedThemePref;
    ImageButton instagramBtn,facebookBtn,webBtn,infoBtn,flagshipBtn,eventsBtn,guestSpeakerBtn,leaderBoardBtn,sponsorsBtn;
    Toolbar toolbar;
    Button ok;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hp_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_s: {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Get ready to explore the cosmos realm and beyond on our very own Pantheon App. Available for Android on Google PlayStore. Download Now !! http://play.google.com/store/apps/details?id=com.kraken.pantheon19");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Cool, how do you wanna share..?"));
                break;
            }
            case R.id.action_pp: {
                startActivity(new Intent(this,PrivacyPolicyActivity.class));
                break;
            }
        }
        return true;
    }

    private String readTextFromResource(int privacy_policy_text) {
        InputStream raw = getResources().openRawResource(privacy_policy_text);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        int i;
        try
        {
            i = raw.read();
            while (i != -1)
            {
                stream.write(i);
                i = raw.read();
            }
            raw.close();
        }
	    catch (IOException e){ e.printStackTrace(); }
        return stream.toString();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        infoBtn = findViewById(R.id.info_button);
        flagshipBtn = findViewById(R.id.flagship_list_button);
        eventsBtn = findViewById(R.id.event_list_button);
        guestSpeakerBtn = findViewById(R.id.guest_speaker_btn);
        leaderBoardBtn = findViewById(R.id.leaderboard_btn);
        sponsorsBtn = findViewById(R.id.sponsors_btn);
        facebookBtn = findViewById(R.id.facebook_btn);
        instagramBtn = findViewById(R.id.instagram_btn);
        webBtn = findViewById(R.id.web_btn);
        //ImageView shareBtn = findViewById(R.id.home_share_btn);
        constraintLayout=findViewById(R.id.home_layout);
        welcome=findViewById(R.id.pantheon_19_title);
        des=findViewById(R.id.pantheon_19_description);
        info=findViewById(R.id.info_home_layout);
        flag=findViewById(R.id.flagship_home_layout);
        event=findViewById(R.id.events_home_layout);
        speaker=findViewById(R.id.speaker_home_layout);
        lb=findViewById(R.id.lb_home_layout);
        sponsor=findViewById(R.id.sponsors_home_layout);
        infotext=findViewById(R.id.info_home_text);
        flagtext=findViewById(R.id.flagship_home_text);
        eventtext=findViewById(R.id.events_home_text);
        speakertext=findViewById(R.id.speakers_home_text);
        lbtext=findViewById(R.id.lb_home_text);
        sponsortext=findViewById(R.id.sponsors_home_text);
        imageView=findViewById(R.id.theme_switcher);
        sharedThemePref=new SharedThemePref();
        toolbar=findViewById(R.id.app_bar);

        //set drop down
        setSupportActionBar(toolbar);

        //set theme
        isDark = sharedThemePref.getThemeStatePref(this);
        if(isDark) setDarkTheme();
        else setLightTheme();

        // set onclick listener
        infoBtn.setOnClickListener(this);
        flagshipBtn.setOnClickListener(this);
        eventsBtn.setOnClickListener(this);
        guestSpeakerBtn.setOnClickListener(this);
        leaderBoardBtn.setOnClickListener(this);
        sponsorsBtn.setOnClickListener(this);
        facebookBtn.setOnClickListener(this);
        instagramBtn.setOnClickListener(this);
        webBtn.setOnClickListener(this);
        //shareBtn.setOnClickListener(this);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isDark=!isDark;
                if(isDark) setDarkTheme();
                else setLightTheme();
                sharedThemePref.saveThemeStatePref(isDark,HomepageActivity.this);
            }
        });

        //For Notification

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("MyNotifications","MyNotifications", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().subscribeToTopic("pantheon")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg="Successful";
                        if(!task.isSuccessful()){
                            msg="Failed";
                        }
                        //Toast.makeText(HomepageActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        // create custom tab
        customTabsIntent = new CustomTabsIntent.Builder()
                .setShowTitle(true)
                .build();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.info_button:
                startActivity(new Intent(this, AboutUsActivity.class));
                break;
            case R.id.flagship_list_button:
                startActivity(new Intent(this, FlagshipEventActivity.class));
                break;
            case R.id.event_list_button:
                startActivity(new Intent(this, EventsActivity.class));
                break;
            case R.id.guest_speaker_btn:
                startActivity(new Intent(this, SpeakersListActivity.class));
                break;
            case R.id.leaderboard_btn:
                startActivity(new Intent(this, LeaderboardActivity.class));
                break;
            case R.id.sponsors_btn:
                startActivity(new Intent(this, SponsorsActivity.class));
                break;
            case R.id.facebook_btn:
                launchCustomTab(Constants.FACEBOOK_URL);
                break;
            case R.id.instagram_btn:
                launchCustomTab(Constants.INSTAGRAM_URL);
                break;
            case R.id.web_btn:
                launchCustomTab(Constants.WEBSITE_URL);
                break;
//            case R.id.home_share_btn:
//                Log.d(TAG, "onClick: share btn");
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                intent.putExtra(Intent.EXTRA_TEXT, "Hey check out pantheon's app on Google play store");
//                intent.setType("text/plain");
//                startActivity(Intent.createChooser(intent, "Cool, how do you wanna share..?"));
//                break;
        }
    }

    private void launchCustomTab(String url) {
        Uri uri = Uri.parse(url);
        customTabsIntent.launchUrl(this, uri);
    }

    public void setLightTheme() {
        //setting background color of homepage
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.md_white_1000));
        //setting colors of textviews in homepage
        welcome.setTextColor(getResources().getColor(R.color.md_black_1000));
        des.setTextColor(getResources().getColor(R.color.md_black_1000));
        //setting colors of social buttons
        facebookBtn.setColorFilter(getResources().getColor(R.color.md_black_1000));
        instagramBtn.setColorFilter(getResources().getColor(R.color.md_black_1000));
        webBtn.setColorFilter(getResources().getColor(R.color.md_black_1000));
        //setting colors of buttons in homepage
        info.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        infoBtn.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        flag.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        flagshipBtn.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        event.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        eventsBtn.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        speaker.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        guestSpeakerBtn.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        lb.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        leaderBoardBtn.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        sponsor.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        sponsorsBtn.setBackgroundColor(getResources().getColor(R.color.md_grey_300));
        //setting colors of text in buttons
        infotext.setTextColor(getResources().getColor(R.color.md_black_1000));
        flagtext.setTextColor(getResources().getColor(R.color.md_black_1000));
        eventtext.setTextColor(getResources().getColor(R.color.md_black_1000));
        speakertext.setTextColor(getResources().getColor(R.color.md_black_1000));
        lbtext.setTextColor(getResources().getColor(R.color.md_black_1000));
        sponsortext.setTextColor(getResources().getColor(R.color.md_black_1000));
        //set status bar
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.md_white_1000));// set status background white
    }

    public void setDarkTheme() {
        //setting background color of homepage
        constraintLayout.setBackgroundColor(getResources().getColor(R.color.md_black_1000));
        //setting colors of textviews in homepage
        welcome.setTextColor(getResources().getColor(R.color.md_white_1000));
        des.setTextColor(getResources().getColor(R.color.md_white_1000));
        //setting colors of social buttons
        facebookBtn.setColorFilter(getResources().getColor(R.color.md_white_1000));
        instagramBtn.setColorFilter(getResources().getColor(R.color.md_white_1000));
        webBtn.setColorFilter(getResources().getColor(R.color.md_white_1000));
        //setting colors of buttons in homepage
        info.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        infoBtn.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        flag.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        flagshipBtn.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        event.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        eventsBtn.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        speaker.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        guestSpeakerBtn.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        lb.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        leaderBoardBtn.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        sponsor.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        sponsorsBtn.setBackgroundColor(getResources().getColor(R.color.accent_grey));
        //setting colors of text in buttons
        infotext.setTextColor(getResources().getColor(R.color.md_white_1000));
        flagtext.setTextColor(getResources().getColor(R.color.md_white_1000));
        eventtext.setTextColor(getResources().getColor(R.color.md_white_1000));
        speakertext.setTextColor(getResources().getColor(R.color.md_white_1000));
        lbtext.setTextColor(getResources().getColor(R.color.md_white_1000));
        sponsortext.setTextColor(getResources().getColor(R.color.md_white_1000));
        //set status bar
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.md_black_1000));
        View decorView = getWindow().getDecorView(); //set status background black
        decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); //set status text  ligh
    }
}
