package com.ezz.data.remote.di;

import android.content.Context;

import com.ezz.data.di.DataScope;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

import static com.ezz.data.remote.di.NetworkModule.OK_HTTP_PICASSO;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Module(includes = {NetworkModule.class})
public class PicassoModule {

	@Provides
	public static Downloader downloader(@Named(OK_HTTP_PICASSO) OkHttpClient okHttpClient) {
		return new OkHttp3Downloader(okHttpClient);
	}

	@Provides
	@DataScope
	public static Picasso picasso(Context context, Downloader downloader) {
		return new Picasso.Builder(context)
		.downloader(downloader)
		.build();
	}
}
