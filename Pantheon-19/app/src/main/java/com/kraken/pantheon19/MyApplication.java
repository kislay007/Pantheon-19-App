package com.kraken.pantheon19;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MyApplication extends Application {

    private static RequestQueue volleyRequestQueue;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        volleyRequestQueue = Volley.newRequestQueue(this);
        context = getApplicationContext();
    }

    public static RequestQueue getVolleyRequestQueue() {
        return volleyRequestQueue;
    }

    public static Context getContext() {
        return context;
    }
}
