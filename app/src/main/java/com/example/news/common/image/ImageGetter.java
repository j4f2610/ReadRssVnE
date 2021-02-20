package com.example.news.common.image;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;

public class ImageGetter implements Html.ImageGetter {
    @Override
    public Drawable getDrawable(String source) {
        return null;
    }

    class BitmapDrawablePlaceHolder extends BitmapDrawable {
        private Drawable drawable;

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }

        public BitmapDrawablePlaceHolder(Resources res, Bitmap bitmap) {

        }

        @Override
        public void draw(Canvas canvas) {
            if (drawable != null) {
                draw(canvas);
            }
        }
    }
}
