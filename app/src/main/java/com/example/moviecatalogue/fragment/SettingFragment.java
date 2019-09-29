package com.example.moviecatalogue.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.services.DailyReminder;
import com.example.moviecatalogue.services.ReleaseTodayReminder;
import com.example.moviecatalogue.settings.Settings;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends Fragment {

    private Settings preferences;

    Switch daily, release;

    DailyReminder dailyReminder;
    ReleaseTodayReminder releaseToday;

    public SettingFragment() {
        // Required empty public constructor
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        preferences = new Settings(getContext());

        dailyReminder = new DailyReminder();
        releaseToday = new ReleaseTodayReminder();

        daily = view.findViewById(R.id.switch_daily_reminder);
        release = view.findViewById(R.id.switch_release_today);

        daily.setChecked(preferences.dailyChecked());
        release.setChecked(preferences.releaseChecked());

        daily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checked = daily.isChecked();
                if (checked) {
                    dailyReminder.setDailyReminder(getContext(),
                            DailyReminder.Repeating, "07:00", getString(R.string.notif_msg));
                } else {
                    dailyReminder.cancelDailyReminder(getContext(),
                            DailyReminder.Repeating);
                }
                preferences.SetDailyReminder(checked);
            }
        });
        release.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                boolean checked = release.isChecked();
                if (checked) {
                    releaseToday.setMovieReleaseNotif(getContext(),
                            ReleaseTodayReminder.Repeating, "08:00");
                } else {
                    releaseToday.cancelMovieNotif(getContext(),
                            ReleaseTodayReminder.Repeating);
                }
                preferences.SetReleaseReminder(checked);
            }
        });
        return view;
    }
}

