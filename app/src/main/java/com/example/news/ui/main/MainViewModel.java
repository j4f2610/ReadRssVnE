package com.example.news.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.news.model.RssFeed;
import com.example.news.repository.RssRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private RssRepository rssRepository = new RssRepository();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData _RssChannel = new MutableLiveData();
    public LiveData<RssFeed> rssChannel = _RssChannel;

    public void getRssChannel() {
        compositeDisposable.add(rssRepository.getRssList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response != null) {
                        _RssChannel.postValue(response);
                    }
                }, throwable -> {
                    throwable.printStackTrace();
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}