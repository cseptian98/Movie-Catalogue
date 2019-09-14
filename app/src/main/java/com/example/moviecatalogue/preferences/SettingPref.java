package com.example.moviecatalogue.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingPref {
    private String Key_Release = "upcoming";
    private String Key_Daily = "daily";
    private String Pref_Name = "UserPref";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    public SettingPref(Context context){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    public void SetDailyReminder(boolean check){
        editor = preferences.edit();
        editor.putBoolean(Key_Daily, check);
        editor.apply();
    }

    public void SetReleaseReminder(boolean check){
        editor = preferences.edit();
        editor.putBoolean(Key_Release, check);
        editor.apply();
    }

    public boolean dailyChecked() {
        return preferences.getBoolean(Key_Daily, false);
    }

    public boolean releaseChecked(){
        return preferences.getBoolean(Key_Release, false);
    }
}
