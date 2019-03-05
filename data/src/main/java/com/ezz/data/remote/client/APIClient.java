package com.ezz.data.remote.client;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class APIClient {

    private Retrofit retrofit;

    @Inject
    public APIClient(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    public NewsAPI getNewsAPI(){
        return retrofit.create(NewsAPI.class);
    }

}
