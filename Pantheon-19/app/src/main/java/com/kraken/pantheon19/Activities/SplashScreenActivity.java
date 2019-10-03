package com.kraken.pantheon19.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kraken.pantheon19.NetworkServices.ApiService;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.Constants;

public class SplashScreenActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ApiService service = new ApiService();
        service.getFormalEvents(this, Constants.FORMAL_EVENTS_API);
        service.getInformalEvents(this, Constants.INFORMAL_EVENTS_API);
        imageView=(ImageView) findViewById(R.id.splashfront);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mt);
        imageView.startAnimation(animation);
        Thread timer=new Thread(){
            public void run(){
                try{
                    sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    startActivity(new Intent(SplashScreenActivity.this,HomepageActivity.class));
                    finish();
                }
            }
        };
        timer.start();
    }
}

