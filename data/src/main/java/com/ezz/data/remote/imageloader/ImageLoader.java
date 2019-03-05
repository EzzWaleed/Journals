package com.ezz.data.remote.imageloader;

import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface ImageLoader {

    void loadImageWithProgress(ImageView imageView, final ProgressBar progressBar, @Nullable String url);

    void loadImageWithProgress(ImageView imageView, final ProgressBar progressBar, @Nullable String url, int placeHolder, int error);

    void loadImageWithoutProgress(ImageView imageView, @Nullable String url);

    void loadImageWithoutProgress(ImageView imageView, @Nullable String url, int placeHolder, int error);
}
