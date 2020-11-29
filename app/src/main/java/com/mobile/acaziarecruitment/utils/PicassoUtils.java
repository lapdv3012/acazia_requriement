package com.mobile.acaziarecruitment.utils;

import android.content.Context;
import android.widget.ImageView;

import com.mobile.acaziarecruitment.R;
import com.mobile.acaziarecruitment.base.BaseActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoUtils {

    public static void loadImage(Context context, String url, final ImageView view) {
        if (context != null && context instanceof BaseActivity && !((BaseActivity) context).isFinishing()) {
            Picasso.get()
                    .load(EmptyUtils.isNotEmpty(url) ? url : null)
                    .priority(Picasso.Priority.NORMAL)
                    .into(view, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
        }
    }

    public static void loadImageLoading(Context context, String url, final ImageView view) {
        if (context != null && context instanceof BaseActivity && !((BaseActivity) context).isFinishing()) {
            Picasso.get()
                    .load(EmptyUtils.isNotEmpty(url) ? url : null)
                    .priority(Picasso.Priority.NORMAL)
                    .into(view, new Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
        }
    }

    public static void loadImage(Context context, String url, final ImageView view, int place_holder) {
        if (context != null && context instanceof BaseActivity && !((BaseActivity) context).isFinishing()) {
            Picasso.get()
                    .load(EmptyUtils.isNotEmpty(url) ? url : null)
                    .noFade()
                    .priority(Picasso.Priority.NORMAL)
                    .error(place_holder)
                    .placeholder(place_holder)
                    .into(view);
        }
    }

    public static void loadImage(Context context, int id, ImageView view) {
        if (context != null && context instanceof BaseActivity && !((BaseActivity) context).isFinishing()) {
            Picasso.get()
                    .load(id)
                    .priority(Picasso.Priority.HIGH)
                    .noFade()
                    // .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(view);
        }
    }
}
