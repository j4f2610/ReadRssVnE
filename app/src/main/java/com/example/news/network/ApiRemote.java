package com.example.news.network;

import com.example.news.model.RssFeed;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiRemote {
    @GET("tin-moi-nhat.rss")
    Single<RssFeed> getFeed();
}
