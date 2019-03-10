package com.ezz.presentation.viewmodel.search;

import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.usecase.SearchNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.search.datasource.SearchDataSourceFactory;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.Scheduler;

import static com.ezz.presentation.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.presentation.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class SearchViewModel extends ViewModel {

	private SearchNewsUsecase searchNewsUsecase;

	private NewsMapper newsMapper;

	private SearchDataSourceFactory searchDataSourceFactory;

	//Rx schedulers
	private final Scheduler subscribeOn;
	private final Scheduler observeOn;

	//Exposed liveData
	private MutableLiveData<PagedList<NewsUI>> newsLiveData = new MutableLiveData<>();
	private MutableLiveData<DataStatus> dataStatusLiveData = new MutableLiveData<>();

	//DataSource liveData
	private LiveData<PagedList<NewsUI>> dataSourceFactoryLiveData;
	private LiveData<DataStatus> dataSourceStatusLiveData;

	//DataSource observers
	private Observer<PagedList<NewsUI>> pagedListObserver = newsUIPagedList -> newsLiveData.setValue(newsUIPagedList);
	private Observer<DataStatus> dataStatusObserver = dataStatus -> dataStatusLiveData.setValue(dataStatus);

	@Inject
	public SearchViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, SearchNewsUsecase searchNewsUsecase, NewsMapper newsMapper) {
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
		this.searchNewsUsecase = searchNewsUsecase;
		this.newsMapper = newsMapper;
	}

	public void searchFor(String query) {

		//Remove old dataSource live data observers if exist.
		if (searchDataSourceFactory != null) {
			dataSourceFactoryLiveData.removeObserver(pagedListObserver);
			dataSourceStatusLiveData.removeObserver(dataStatusObserver);
		}

		searchDataSourceFactory = new SearchDataSourceFactory(subscribeOn, observeOn, searchNewsUsecase, newsMapper, query);

		//Assign the new dataSource liveData to the view model global variables
		dataSourceFactoryLiveData = new LivePagedListBuilder<>(searchDataSourceFactory, 10).build();
		dataSourceStatusLiveData = searchDataSourceFactory.getDataStatusLiveData();

		//Observe on the new dataSource liveData
		dataSourceFactoryLiveData.observeForever(pagedListObserver);
		dataSourceStatusLiveData.observeForever(dataStatusObserver);
	}

	public MutableLiveData<PagedList<NewsUI>> getNewsLiveData() {
		return newsLiveData;
	}

	public MutableLiveData<DataStatus> getDataStatusLiveData() {
		return dataStatusLiveData;
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		removeObservers();
	}

	/**
	 * Dispose Rx and remove liveData observers.
	 */
	private void removeObservers() {
		if (searchDataSourceFactory != null) {
			searchDataSourceFactory.dispose();
		}
		if (dataSourceFactoryLiveData != null) {
			dataSourceFactoryLiveData.removeObserver(pagedListObserver);
		}
		if (dataSourceStatusLiveData != null) {
			dataSourceStatusLiveData.removeObserver(dataStatusObserver);
		}
	}
}
