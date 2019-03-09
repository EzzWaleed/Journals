package com.ezz.data.remote.client;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ezz Waleed on 04,March,2019
 */

/**
 * APIInterceptor Intercepts every network request to add default query parameters to it.
 **/
public class APIInterceptor implements Interceptor {

	@Inject
	APIInterceptor() {
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request original = chain.request();
		HttpUrl originalHttpUrl = original.url();


		HttpUrl url = originalHttpUrl.newBuilder()
		//Adding API Key to request url
		.addQueryParameter("apiKey", SettingsAPI.getApiKey())
		//Adding default page size to request url
		.addQueryParameter("pageSize", SettingsAPI.getNumberOfItemsPerPage().toString())
		.build();

		Request.Builder requestBuilder = original.newBuilder().url(url);

		Request request = requestBuilder.build();
		return chain.proceed(request);
	}
}
