package com.ezz.data.datasource;

import android.annotation.SuppressLint;

import com.ezz.data.mapper.DataStatusMapper;
import com.ezz.data.remote.client.NewsAPI;
import com.ezz.data.remote.model.NewsRemote;
import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.resource.Resource;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.paging.PageKeyedDataSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ezz Waleed on 09,March,2019
 */

/**
 * Used to generate paged lists for paging adapter according to the search query.
 */
public class SearchDataSource extends PageKeyedDataSource<Integer, NewsRemote> {

	private CompositeDisposable disposables;
	private Scheduler subscribeOn, observeOn;
	private NewsAPI newsAPI;
	private String searchQuery;
	private boolean hasItemsToLoad = true;
	private DataStatusMapper dataStatusMapper;

	SearchDataSource(Scheduler subscribeOn, Scheduler observeOn, NewsAPI newsAPI, String searchQuery, DataStatusMapper dataStatusMapper) {
		this.newsAPI = newsAPI;
		this.searchQuery = searchQuery;
		this.dataStatusMapper = dataStatusMapper;
		this.disposables = new CompositeDisposable();
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
	}

	@Override
	public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, NewsRemote> callback) {
		executeQuery(1, newsUIList -> callback.onResult(newsUIList, null, 2));
	}

	@Override
	public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsRemote> callback) {
			executeQuery(params.key, newsUIList -> callback.onResult(newsUIList, params.key - 1));
	}

	@Override
	public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsRemote> callback) {
		if (hasItemsToLoad){
			executeQuery(params.key, newsUIList -> callback.onResult(newsUIList, params.key + 1));
		}
		else {
			callback.onResult(new ArrayList<>(), params.key + 1);
		}
	}

	/**
	 * executes the search query and notify with the {@link NewsRemote} response using {@link ExecuteQueryCallBack}.
	 * @param pageNumber requested page number.
	 * @param callBack call back that will be notified when query result returns.
	 */
	private void executeQuery(int pageNumber, ExecuteQueryCallBack callBack) {
		Disposable disposable = newsAPI.searchForNews(searchQuery, pageNumber).map(newsResponse -> {
			DataStatus dataStatus = dataStatusMapper.mapNewsResponseToDataStatus(newsResponse, pageNumber);
			return new Resource<>(dataStatus, newsResponse.getNewsRemotes(), newsResponse.getCode());
		})
		.subscribeOn(subscribeOn)
		.observeOn(observeOn)
		.subscribe(
		newsDomainResource -> {
			callBack.onResult(getData(newsDomainResource));
			hasItemsToLoad = newsDomainResource.status != DataStatus.HAS_LOADED_ALL_ITEMS;
		},
		throwable -> callBack.onResult(new ArrayList<>()));

		addDisposable(disposable);
	}


	/**
	 * Get {@link NewsRemote} list from newsDomainResource according to the {@link DataStatus}.
	 */
	private List<NewsRemote> getData(Resource<List<NewsRemote>> newsDomainResource) {
		return newsDomainResource.status == DataStatus.SUCCESS ? newsDomainResource.data : new ArrayList<>();
	}

	/**
	 * Dispose from current {@link CompositeDisposable}.
	 */
	void dispose() {
		if (!disposables.isDisposed()) {
			disposables.dispose();
		}
	}

	/**
	 * Dispose from current {@link CompositeDisposable}.
	 */
	@SuppressLint("RestrictedApi")
	private void addDisposable(Disposable disposable) {
		Preconditions.checkNotNull(disposable);
		Preconditions.checkNotNull(disposables);
		disposables.add(disposable);
	}


	private interface ExecuteQueryCallBack{
		void onResult(List<NewsRemote> newsUIList);
	}
}
