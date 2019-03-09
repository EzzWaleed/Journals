package com.ezz.data.remote.imageloader;

import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
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
    void loadImageWithProgress(@NonNull ImageView imageView, final ProgressBar progressBar, @Nullable String url);

    /**
     * @param imageView   imageview to load into
     * @param progressBar show and hide progress
     * @param url         url to be loaded
     */
    void loadImageWithProgress(@NonNull ImageView imageView, final ProgressBar progressBar, @Nullable String url, int placeHolder, int error);

    /**
     * Default error resource is transparent
     *
     * @param imageView imageview to load into
     * @param url       url to be loaded
     */
    void loadImageWithoutProgress(@NonNull ImageView imageView, @Nullable String url);

    /**
     * @param imageView imageview to load into
     * @param url       url to be loaded
     */
    void loadImageWithoutProgress(@NonNull ImageView imageView, @Nullable String url, int placeHolder, int error);


    void loadImageWithCallback(@NonNull ImageView imageView, @Nullable String url, @NonNull CallBack callBack);

    /**
     * Loads image through remote source and invokes {@link CallBack} after loading is finish.
     * @param imageView imageView to load into
     * @param url url to be loaded from
     * @param callBack invoked callback
     */
    void loadImageWithCallback(@NonNull ImageView imageView, @Nullable String url, @NonNull CallBack callBack, int placeHolder, int error);

    /**
     * {@link ImageLoader} uses this callback to be invoked after image loading is finished.
     */
    interface CallBack{
        void onFinishLoading();
    }
}
