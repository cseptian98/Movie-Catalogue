package com.example.moviecatalogue.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.moviecatalogue.R;
import com.example.moviecatalogue.fragment.FavoriteFragment;
import com.example.moviecatalogue.fragment.MovieFragment;
import com.example.moviecatalogue.fragment.SearchFragment;
import com.example.moviecatalogue.fragment.SettingFragment;
import com.example.moviecatalogue.fragment.ShowFragment;

public class MainActivity extends AppCompatActivity {

    private int backButtonCount;

    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.view, fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navMovies:
                    changeFragment(MovieFragment.newInstance());
                    return true;
                case R.id.navTvShow:
                    changeFragment(ShowFragment.newInstance());
                    return true;
                case R.id.navSearch:
                    changeFragment(SearchFragment.newInstance());
                    return true;
                case R.id.navFavorite:
                    changeFragment(FavoriteFragment.newInstance());
                    return true;
                case R.id.navSetting:
                    changeFragment(SettingFragment.newInstance());
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeFragment(MovieFragment.newInstance());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press back again to exit.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }
}

