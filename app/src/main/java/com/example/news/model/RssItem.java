package com.example.news.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;
import android.text.Spanned;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.news.NewsApplication;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class RssItem implements Parcelable {
    @Element
    public String title;

    @Element
    public String link;

    @Element
    public String pubDate;

    @Element
    public String description;

    public RssItem() {

    }

    protected RssItem(Parcel in) {
        title = in.readString();
        link = in.readString();
        pubDate = in.readString();
        description = in.readString();
    }

    public Spanned getDescriptionHtml() {
        return Html.fromHtml(description.replaceAll("<img.+?>", ""), HtmlCompat.FROM_HTML_MODE_LEGACY);
    }


    public String getImageThumb() {
        final String[] url = {""};
        Html.fromHtml(description, HtmlCompat.FROM_HTML_MODE_LEGACY, source -> Drawable.createFromPath(url[0] = source), null);
        return url[0];
    }

    public static final Creator<RssItem> CREATOR = new Creator<RssItem>() {
        @Override
        public RssItem createFromParcel(Parcel in) {
            return new RssItem(in);
        }

        @Override
        public RssItem[] newArray(int size) {
            return new RssItem[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(pubDate);
        dest.writeString(description);
    }
}
