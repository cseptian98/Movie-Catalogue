package com.example.moviecatalogue.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.moviecatalogue.widgets.StackView;

public class FavoriteWidget extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackView(this.getApplicationContext(), intent);
    }
}
