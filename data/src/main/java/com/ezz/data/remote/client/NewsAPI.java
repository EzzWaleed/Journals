package com.ezz.data.remote.client;

import com.ezz.data.remote.model.NewsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface NewsAPI {
	@GET("top-headlines")
	Single<NewsResponse> requestTopHeadlines(@Query("page") Integer pageNumber);

	@GET("everything")
	Single<NewsResponse> searchForNews(@Query("q") String query, @Query("page") Integer pageNumber);
}
