package com.example.moviecatalogue.services;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.moviecatalogue.widgets.StackRemoteViewsFactory;

public class FavoriteWidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent){
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
