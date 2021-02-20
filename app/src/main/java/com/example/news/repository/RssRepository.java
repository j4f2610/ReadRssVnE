package com.example.news.repository;

import com.example.news.model.RssChannel;
import com.example.news.model.RssFeed;
import com.example.news.network.ApiRemote;
import com.example.news.network.HttpEngine;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RssRepository {

    private ApiRemote apiRemote = HttpEngine.getInstance().createService(ApiRemote.class);

    public Single<RssFeed> getRssList() {
        return apiRemote.getFeed();
    }
}
