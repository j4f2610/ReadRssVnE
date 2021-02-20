package com.example.news;

import android.app.Application;

public class NewsApplication extends Application {
    public static NewsApplication newsApplication;

    public static NewsApplication getInstance() {
        if (newsApplication == null) {
            newsApplication = new NewsApplication();
        }
        return newsApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        newsApplication = this;
    }
}
