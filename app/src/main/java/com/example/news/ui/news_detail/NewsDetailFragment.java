package com.example.news.ui.news_detail;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewFeature;

import com.example.news.R;
import com.example.news.databinding.NewsDetailFragmentBinding;
import com.example.news.model.RssItem;

public class NewsDetailFragment extends Fragment {
    private NewsDetailFragmentBinding binding;
    private RssItem mRssItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.news_detail_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRssItem = NewsDetailFragmentArgs.fromBundle(getArguments()).getRssItem();
        binding.wvNewsFeed.loadUrl(mRssItem.link);
        int currentNightMode = getActivity().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
            switch (currentNightMode) {
                case Configuration.UI_MODE_NIGHT_NO:
                    WebSettingsCompat.setForceDark(binding.wvNewsFeed.getSettings(), WebSettingsCompat.FORCE_DARK_OFF);
                    break;
                case Configuration.UI_MODE_NIGHT_YES:

                    WebSettingsCompat.setForceDark(binding.wvNewsFeed.getSettings(), WebSettingsCompat.FORCE_DARK_ON);
                    break;
            }
        }
    }

}