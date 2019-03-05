package com.ezz.data.remote.imageloader;

import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface ImageLoader {

    /**
     * Default error resource is white
     *
     * @param imageView   imageview to load into
     * @param progressBar show and hide progress
     * @param url         url to be loaded
     */
    void loadImageWithProgress(ImageView imageView, final ProgressBar progressBar, @Nullable String url);

    /**
     * @param imageView   imageview to load into
     * @param progressBar show and hide progress
     * @param url         url to be loaded
     */
    void loadImageWithProgress(ImageView imageView, final ProgressBar progressBar, @Nullable String url, int placeHolder, int error);

    /**
     * Default error resource is transparent
     *
     * @param imageView imageview to load into
     * @param url       url to be loaded
     */
    void loadImageWithoutProgress(ImageView imageView, @Nullable String url);

    /**
     * @param imageView imageview to load into
     * @param url       url to be loaded
     */
    void loadImageWithoutProgress(ImageView imageView, @Nullable String url, int placeHolder, int error);
}
