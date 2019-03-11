package com.ezz.presentation.viewmodel.search;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.SearchNewsUseCase;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.paging.PagedList;
import io.reactivex.Scheduler;

import static com.ezz.data.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.data.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class SearchViewModel extends BaseViewModel {

	private String searchQueryState;

	private SearchNewsUseCase<NewsUI> searchNewsUseCase;

	private MutableLiveData<PagedList<NewsUI>> pagedListLiveData = new MutableLiveData<>();

	private MutableLiveData<DataStatus> pagingDataStatus = new MutableLiveData<>();

	@Inject
	SearchViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, SearchNewsUseCase<NewsUI> searchNewsUseCase) {
		super(subscribeOn, observeOn);
		this.searchNewsUseCase = searchNewsUseCase;
	}

	/**
	 * for the firstTime called creates a new pagedList stream, then the other calls updates dataSource query.
	 * @param query search query
	 */
	public void searchFor(String query) {
		if (searchQueryState == null){
			createPagedList(query);
		}else {
			searchNewsUseCase.updateSearchQuery(query);
		}
		searchQueryState = query;
	}

	/**
	 * creates PagedList stream according to search query.
	 * @param query search query
	 */
	private void createPagedList(String query){
		execute(
		disposable -> pagingDataStatus.postValue(DataStatus.LOADING),
		newsUIPagedList -> {
			pagedListLiveData.postValue(newsUIPagedList);
			pagingDataStatus.setValue(DataStatus.SUCCESS);
		},
		throwable -> pagingDataStatus.postValue(DataStatus.ERROR),
		searchNewsUseCase.getSearchPagedList(query)
		);
	}

	public LiveData<PagedList<NewsUI>> getNewsLiveData() {
		return pagedListLiveData;
	}

	public MutableLiveData<DataStatus> getPagingDataStatus() {
		return pagingDataStatus;
	}

	public String getSearchQueryState() {
		return searchQueryState;
	}

}
