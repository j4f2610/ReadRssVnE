package com.example.news.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news.R;
import com.example.news.databinding.NewsItemLayoutBinding;
import com.example.news.model.RssItem;

import java.util.ArrayList;
import java.util.List;

public class NewsFeedAdapter extends RecyclerView.Adapter<NewsFeedAdapter.NewsFeedViewHolder> {
    private List<RssItem> data = new ArrayList<>();
    private NewsFeedAdapterListener mNewsFeedAdapterListener;

    public NewsFeedAdapter(NewsFeedAdapterListener newsFeedAdapterListener) {
        mNewsFeedAdapterListener = newsFeedAdapterListener;
    }

    public void setData(List<RssItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemLayoutBinding newsItemLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item_layout, parent, false);
        return new NewsFeedViewHolder(newsItemLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsFeedViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class NewsFeedViewHolder extends RecyclerView.ViewHolder {
        private final NewsItemLayoutBinding binding;

        public NewsFeedViewHolder(NewsItemLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(RssItem item) {
            binding.setItem(item);
            binding.llNewsFeed.setOnClickListener(v -> {
                mNewsFeedAdapterListener.NewsFeedAdapterItemOnClick(item);
            });
            binding.executePendingBindings();
        }
    }

    public interface NewsFeedAdapterListener {
        void NewsFeedAdapterItemOnClick(RssItem item);
    }
}
