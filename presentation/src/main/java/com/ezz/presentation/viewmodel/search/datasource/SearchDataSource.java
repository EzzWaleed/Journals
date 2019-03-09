package com.ezz.presentation.viewmodel.search.datasource;

import android.annotation.SuppressLint;

import com.ezz.domain.usecase.SearchNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;

import java.util.ArrayList;

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
public class SearchDataSource extends PageKeyedDataSource<Integer, NewsUI> {

	private CompositeDisposable disposables;
	private Scheduler subscribeOn, observeOn;
	private SearchNewsUsecase searchNewsUsecase;
	private NewsMapper newsMapper;
	private String searchQuery;
	PagedCallbackManger pagedCallbackManger = new PagedCallbackManger();

	public SearchDataSource(Scheduler subscribeOn, Scheduler observeOn, SearchNewsUsecase searchNewsUsecase, NewsMapper newsMapper, String searchQuery) {
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
		.subscribeOn(subscribeOn)
		.observeOn(observeOn)
		.subscribe(
		newsDomainResource ->{
			PagedCallbackManger.PagedCallbackHandler handler = pagedCallbackManger.createPagedCallbackHandler(newsDomainResource, null);
			callback.onResult(handler.getData(), handler.getPreviousPaggedKey(), handler.getNextPaggedKey());
			},
		throwable -> callback.onResult(new ArrayList<>(), null, 1));

		addDisposable(disposable);
	}

	@Override
	public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsUI> callback) {
		if (!pagedCallbackManger.hasLoadedAllItems()){
			Disposable disposable = searchNewsUsecase.searchNews(searchQuery, params.key).map(newsDomainResource -> newsMapper.mapToUIResourceList(newsDomainResource))
			.subscribeOn(subscribeOn)
			.observeOn(observeOn)
			.subscribe(
			newsDomainResource -> {
				PagedCallbackManger.PagedCallbackHandler handler = pagedCallbackManger.createPagedCallbackHandler(newsDomainResource, params);
				callback.onResult(handler.getData(), handler.getNextPaggedKey());
			},
			throwable -> callback.onResult(new ArrayList<>(), params.key - 1));

			addDisposable(disposable);
		}
		else {
			callback.onResult(new ArrayList<>(), params.key - 1);
		}
	}

	@Override
	public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, NewsUI> callback) {
		if (!pagedCallbackManger.hasLoadedAllItems()){
			Disposable disposable = searchNewsUsecase.searchNews(searchQuery, params.key).map(newsDomainResource -> newsMapper.mapToUIResourceList(newsDomainResource))
			.subscribeOn(subscribeOn)
			.observeOn(observeOn)
			.subscribe(
			newsDomainResource -> {
				PagedCallbackManger.PagedCallbackHandler handler = pagedCallbackManger.createPagedCallbackHandler(newsDomainResource, params);
				callback.onResult(handler.getData(), handler.getNextPaggedKey());
			},
			throwable -> callback.onResult(new ArrayList<>(), params.key + 1));

			addDisposable(disposable);
		}
		else {
			callback.onResult(new ArrayList<>(), params.key + 1);
		}
	}

	/**
	 * Dispose from current {@link CompositeDisposable}.
	 */
	public void dispose() {
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
}
