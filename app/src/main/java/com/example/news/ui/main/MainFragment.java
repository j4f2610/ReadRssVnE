package com.example.news.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.news.MainActivity;
import com.example.news.R;
import com.example.news.adapter.NewsFeedAdapter;
import com.example.news.databinding.MainFragmentBinding;
import com.example.news.model.RssItem;

public class MainFragment extends Fragment {
    private MainFragmentBinding mainFragmentBinding;
    private MainViewModel mViewModel;
    private NewsFeedAdapter newsFeedAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.main_fragment, container, false);
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mViewModel.getRssChannel();
        newsFeedAdapter = new NewsFeedAdapter(item -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navController.navigate(
                        MainFragmentDirections.actionMainToNewsDetail().setRssItem(item)
                );
            }
        });
        mainFragmentBinding.rcvNews.setAdapter(newsFeedAdapter);
        mViewModel.rssChannel.observe(getViewLifecycleOwner(), rssFeed -> {
            if (rssFeed.channel != null && rssFeed.channel.item != null) {
                newsFeedAdapter.setData(rssFeed.channel.item);
            }
        });
    }
}