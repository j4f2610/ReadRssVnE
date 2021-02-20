package com.example.news.common.binding;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.news.NewsApplication;
import com.example.news.R;

public class BindingHandler {
    @BindingAdapter("textToHtml")
    public static void textToHtml(TextView view, String text) {
        Spanned spanned;
        spanned = Html.fromHtml(text, source -> {
            final Drawable[] d = {null};
            Context c = NewsApplication.getInstance().getApplicationContext();
            Glide.with(view.getContext())
                    .asBitmap()
                    .load(source)
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            BitmapDrawable background = new BitmapDrawable(view.getContext().getResources(), resource);
                            background.setBounds(0, 0, 200, 200);
                            view.setCompoundDrawables(background, null, null, null);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                        }
                    });
            return d[0];
        }, null);
        view.setText(spanned);
    }

    @BindingAdapter("imageUrl")
    public static void imageUrl(ImageView view, String text) {

        Glide.with(view.getContext())
                .asBitmap()
                .load(text)
                .placeholder(R.drawable.ic_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);


    }

}
