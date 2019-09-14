package com.example.moviecatalogue.activity;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.preferences.SettingPref;
import com.example.moviecatalogue.services.DailyReminderService;
import com.example.moviecatalogue.services.ReleaseTodayReminderService;

public class SettingActivity extends AppCompatActivity {

    private SettingPref preferences;

    Switch daily, release;
    TextView language;

    DailyReminderService dailyReminder;
    ReleaseTodayReminderService releaseTodayTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        getSupportActionBar().setTitle(R.string.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        preferences = new SettingPref(this);

        dailyReminder = new DailyReminderService();
        releaseTodayTask = new ReleaseTodayReminderService();

        daily = findViewById(R.id.switch_daily_reminder);
        release = findViewById(R.id.switch_release_today);
        language = findViewById(R.id.tv_change_language);

        daily.setChecked(preferences.dailyChecked());
        release.setChecked(preferences.releaseChecked());

        daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checked = daily.isChecked();
                if(checked){
                    dailyReminder.setDailyReminderNotif(getApplicationContext(),
                            DailyReminderService.Type_Repeating, "07:00", getString(R.string.notif_msg));
                } else {
                    dailyReminder.cancelDailyReminder(getApplicationContext(),
                            DailyReminderService.Type_Repeating);
                }
                preferences.SetDailyReminder(checked);
            }
        });
        release.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checked = release.isChecked();
                if(checked) {
                    releaseTodayTask.setMovieReleaseNotif(getApplicationContext(),
                            ReleaseTodayReminderService.Type_Repeating, "08:00");
                } else {
                    releaseTodayTask.cancelMovieNotif(getApplicationContext(),
                            ReleaseTodayReminderService.Type_Repeating);
                }
                preferences.SetReleaseReminder(checked);
            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
