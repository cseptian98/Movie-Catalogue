package com.example.moviecatalogue.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.moviecatalogue.Fragment.HomeFragment;
import com.example.moviecatalogue.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.content, new HomeFragment()).commit();
        }
    }
}

