package com.ezz.data.remote.di;



import com.ezz.data.remote.imageloader.ImageLoader;
import com.ezz.data.remote.imageloader.impl.PicassoImageLoader;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Module(includes = PicassoModule.class)
public abstract class ImageLoaderModule {

    @Binds
    abstract ImageLoader provideImageLoader(PicassoImageLoader picassoImageLoader);

}
