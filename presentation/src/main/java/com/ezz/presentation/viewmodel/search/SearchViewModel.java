package com.ezz.presentation.viewmodel.search;

import com.ezz.domain.resource.Resource;
import com.ezz.domain.usecase.SearchNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;
import com.ezz.presentation.viewmodel.search.datasource.SearchDataSource;
import com.ezz.presentation.viewmodel.search.datasource.SearchDataSourceFactory;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

import static com.ezz.presentation.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.presentation.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class SearchViewModel extends ViewModel {

	SearchNewsUsecase searchNewsUsecase;

	NewsMapper newsMapper;

	private final Scheduler subscribeOn;
	private final Scheduler observeOn;

	private SearchDataSourceFactory searchDataSourceFactory;

	MutableLiveData<PagedList<NewsUI>> newsLiveData = new MutableLiveData<>();

	LiveData<PagedList<NewsUI>> dataSourceFactoryLiveData;

	@Inject
	public SearchViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, SearchNewsUsecase searchNewsUsecase, NewsMapper newsMapper) {
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
		this.searchNewsUsecase = searchNewsUsecase;
		this.newsMapper = newsMapper;
	}

	public void searchFor(String query){
		searchDataSourceFactory = new SearchDataSourceFactory(subscribeOn, observeOn, searchNewsUsecase, newsMapper, query);
		if (dataSourceFactoryLiveData != null){
			dataSourceFactoryLiveData.removeObserver(pagedListObserver);
		}
		dataSourceFactoryLiveData = new LivePagedListBuilder<>(searchDataSourceFactory, 10).build();
	}

	Observer<PagedList<NewsUI>> pagedListObserver = newsUIPagedList -> {
		newsLiveData.setValue(newsUIPagedList);
	};


	@Override
	protected void onCleared() {
		super.onCleared();
		if (searchDataSourceFactory != null){
			searchDataSourceFactory.dispose();
		}
		if (dataSourceFactoryLiveData != null){
			dataSourceFactoryLiveData.removeObserver(pagedListObserver);
		}
	}
}
