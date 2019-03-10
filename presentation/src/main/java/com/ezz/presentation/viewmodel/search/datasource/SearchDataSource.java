package com.ezz.presentation.viewmodel.search.datasource;

import android.annotation.SuppressLint;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.resource.Resource;
import com.ezz.domain.usecase.SearchNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.util.Preconditions;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
public class SearchDataSource extends PageKeyedDataSource<Integer, NewsUI> {

	private CompositeDisposable disposables;
	private Scheduler subscribeOn, observeOn;
	private SearchNewsUsecase searchNewsUsecase;
	private NewsMapper newsMapper;
	private String searchQuery;
	private MutableLiveData<DataStatus> dataStatusLiveData = new MutableLiveData<>();
	private boolean hasItemsToLoad = true;

	SearchDataSource(Scheduler subscribeOn, Scheduler observeOn, SearchNewsUsecase searchNewsUsecase, NewsMapper newsMapper, String searchQuery) {
		this.disposables = new CompositeDisposable();
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
		this.searchNewsUsecase = searchNewsUsecase;
		this.newsMapper = newsMapper;
		this.searchQuery = searchQuery;
	}

	@Override
	public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, NewsUI> callback) {
		Disposable disposable = searchNewsUsecase.searchNews(searchQuery, 1).map(newsDomainResource -> newsMapper.mapToUIResourceList(newsDomainResource))
		.doOnSubscribe(subscribedDisposable -> dataStatusLiveData.postValue(DataStatus.LOADING))
		.subscribeOn(subscribeOn)
		.observeOn(observeOn)
		.subscribe(
		newsDomainResource ->{
			callback.onResult(getData(newsDomainResource), null,1);
			dataStatusLiveData.postValue(newsDomainResource.status);
			hasItemsToLoad = newsDomainResource.status != DataStatus.HAS_LOADED_ALL_ITEMS;
			},
		throwable -> {
			callback.onResult(new ArrayList<>(), null, 1);
			dataStatusLiveData.postValue(DataStatus.ERROR);
		});

		addDisposable(disposable);
	}

	@Override
	public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsUI> callback) {
			Disposable disposable = searchNewsUsecase.searchNews(searchQuery, params.key).map(newsDomainResource -> newsMapper.mapToUIResourceList(newsDomainResource))
			.subscribeOn(subscribeOn)
			.observeOn(observeOn)
			.subscribe(
			newsDomainResource -> {
				callback.onResult(getData(newsDomainResource), params.key - 1);
				dataStatusLiveData.postValue(newsDomainResource.status);
				hasItemsToLoad = newsDomainResource.status != DataStatus.HAS_LOADED_ALL_ITEMS;
			},
			throwable -> {
				callback.onResult(new ArrayList<>(), params.key - 1);
				dataStatusLiveData.postValue(DataStatus.ERROR);
			});

			addDisposable(disposable);
	}

	@Override
	public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsUI> callback) {
		if (hasItemsToLoad){
			Disposable disposable = searchNewsUsecase.searchNews(searchQuery, params.key).map(newsDomainResource -> newsMapper.mapToUIResourceList(newsDomainResource))
			.subscribeOn(subscribeOn)
			.observeOn(observeOn)
			.subscribe(
			newsDomainResource -> {
				callback.onResult(getData(newsDomainResource), params.key + 1);
				dataStatusLiveData.postValue(newsDomainResource.status);
				hasItemsToLoad = newsDomainResource.status != DataStatus.HAS_LOADED_ALL_ITEMS;
			},
			throwable -> callback.onResult(new ArrayList<>(), params.key + 1));

			addDisposable(disposable);
		}
		else {
			callback.onResult(new ArrayList<>(), params.key + 1);
		}
	}

	/**
	 * Get {@link NewsUI} list from newsDomainResource according to the {@link DataStatus}.
	 */
	private List<NewsUI> getData(Resource<List<NewsUI>> newsDomainResource) {
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

	public LiveData<DataStatus> getDataStatusLiveData() {
		return dataStatusLiveData;
	}
}
