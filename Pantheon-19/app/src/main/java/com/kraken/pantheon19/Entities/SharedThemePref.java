package com.kraken.pantheon19.Entities;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedThemePref {
    public void saveThemeStatePref(boolean isDark, Context context) {

        SharedPreferences pref = context.getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isDark",isDark);
        editor.apply();
    }

    public boolean getThemeStatePref (Context context) {

        SharedPreferences pref = context.getSharedPreferences("myPref",MODE_PRIVATE);
        boolean isDark = pref.getBoolean("isDark",true) ;
        return isDark;

    }
}
