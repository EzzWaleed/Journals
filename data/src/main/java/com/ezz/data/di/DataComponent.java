package com.ezz.data.di;

import android.content.Context;


import com.ezz.data.remote.di.ImageLoaderModule;
import com.ezz.data.remote.di.NetworkModule;
import com.ezz.data.remote.imageloader.ImageLoader;

import dagger.BindsInstance;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Component(modules = {NetworkModule.class, ImageLoaderModule.class})
@DataScope
public interface DataComponent {
    Retrofit retrofit();
    ImageLoader imageLoader();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder appContext(Context context);

        DataComponent build();
    }
}
