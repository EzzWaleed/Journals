package com.ezz.data.remote.imageloader.impl;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;


import com.ezz.data.R;
import com.ezz.data.remote.imageloader.ImageLoader;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;


/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class PicassoImageLoader implements ImageLoader {

    private Picasso picasso;

    @Inject
    public PicassoImageLoader(Picasso picasso) {
        this.picasso = picasso;
    }

    /**
     * Default error resource is white
     *
     * @param imageView   imageview to load into
     * @param progressBar show and hide progress
     * @param url         url to be loaded
     */
    @Override
    public void loadImageWithProgress(final ImageView imageView, final ProgressBar progressBar, String url) {
        loadImageWithProgress(imageView, progressBar, url, R.color.transparent, R.color.white);
    }


    /**
     * @param imageView   imageview to load into
     * @param progressBar show and hide progress
     * @param url         url to be loaded
     */
    @Override
    public void loadImageWithProgress(final ImageView imageView, final ProgressBar progressBar, String url, int placeHolder, final int error) {
        if (url == null || url.isEmpty()) {
            imageView.setImageResource(error);
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        picasso.load(url).placeholder(placeHolder).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                progressBar.setVisibility(View.GONE);
                imageView.setImageResource(error);
            }
        });
    }

    /**
     * Default error resource is transparent
     *
     * @param imageView imageview to load into
     * @param url       url to be loaded
     */
    @Override
    public void loadImageWithoutProgress(final ImageView imageView, String url) {
        loadImageWithoutProgress(imageView, url, R.color.transparent, R.color.white);
    }

    /**
     * @param imageView imageview to load into
     * @param url       url to be loaded
     */
    @Override
    public void loadImageWithoutProgress(final ImageView imageView, String url, int placeHolder, final int error) {
        if (url == null || url.isEmpty()) {
            imageView.setImageResource(error);
            return;
        }
        picasso.load(url).placeholder(placeHolder).into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                // nothing to do
            }

            @Override
            public void onError(Exception e) {
                imageView.setImageResource(error);
            }
        });
    }
}
