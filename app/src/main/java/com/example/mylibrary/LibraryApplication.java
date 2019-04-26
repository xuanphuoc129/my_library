package com.example.mylibrary;

import android.app.Application;

import com.example.mylibrary.data.DatabaseService;

public class LibraryApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseService.getInstance().createDatabase(getApplicationContext());
    }
}
