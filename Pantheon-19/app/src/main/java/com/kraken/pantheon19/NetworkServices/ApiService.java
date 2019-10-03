package com.kraken.pantheon19.NetworkServices;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.MyApplication;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.DatabaseServiceHelper;
import com.kraken.pantheon19.Utils.Serializer;

import java.util.List;

public class ApiService {
    private static final String TAG = "ApiService";

    /**
     * fetch formal events and add to database
     * @param url : url for formal events
     */
    public void getFormalEvents(final Context context, String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: 200 OK\n" + response);
                // serialize raw response
                List<Event> formalEvents = Serializer.serializeEvents(response);
                Log.d(TAG, "onResponse: " + formalEvents.size());

                for (int i = 0; i < formalEvents.size(); i++) formalEvents.get(i).setTag(context.getResources().getString(R.string.formal_tag));

                Log.d(TAG, "onResponse: " + formalEvents.toString());

                DatabaseServiceHelper.addToEventDb(formalEvents);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });

        MyApplication.getVolleyRequestQueue().add(request);
    }

    /**
     * fetch informal events and add to db
     * @param url : url for informal events
     */
    public void getInformalEvents(final Context context, String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: 200 OK\n" + response);
                // serialize raw response
                List<Event> informalEvents = Serializer.serializeEvents(response);
                Log.d(TAG, "onResponse: " + informalEvents.size());

                for (int i = 0; i < informalEvents.size(); i++) informalEvents.get(i).setTag(context.getResources().getString(R.string.informal_tag));

                Log.d(TAG, "onResponse: " + informalEvents.toString());

                DatabaseServiceHelper.addToEventDb(informalEvents);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });

        MyApplication.getVolleyRequestQueue().add(request);
    }

    /**
     * fetch flagship events and add to db
     * @param url : url for flagship events
     */
    public void getFlagshipEvents(final Context context, String url) {
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "onResponse: 200 OK\n" + response);
                // serialize raw response
                List<Event> flagshipEvents = Serializer.serializeEvents(response);
                Log.d(TAG, "onResponse: " + flagshipEvents.size());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        });

        MyApplication.getVolleyRequestQueue().add(request);
    }
}
