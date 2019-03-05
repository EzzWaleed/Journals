package com.ezz.data.remote.di;


import com.ezz.data.di.DataScope;
import com.ezz.data.remote.client.APIInterceptor;
import com.ezz.data.remote.client.SettingsAPI;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
@Module
public abstract class NetworkModule {

    public static final String  OK_HTTP_RETROFIT = "okHttpRetrofit";
    public static final String  OK_HTTP_PICASSO = "okHttpPicasso";

    @Provides
    @DataScope
    @Named(value = OK_HTTP_RETROFIT)
    public static OkHttpClient provideOkhttpRetrofitClient(APIInterceptor apiInterceptor){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(SettingsAPI.getTimeout(), TimeUnit.MILLISECONDS);
        httpClient.writeTimeout(SettingsAPI.getTimeout(), TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);
        httpClient.addInterceptor(apiInterceptor);

        return httpClient.build();
    }

    @Provides
    @DataScope
    @Named(value = OK_HTTP_PICASSO)
    public static OkHttpClient provideOkhttPicasoClient(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(SettingsAPI.getTimeout(), TimeUnit.MILLISECONDS);
        httpClient.writeTimeout(SettingsAPI.getTimeout(), TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);

        return httpClient.build();
    }



    @Provides
    public static Retrofit provideRetrofit(@Named(value = OK_HTTP_RETROFIT) OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(SettingsAPI.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }


}
