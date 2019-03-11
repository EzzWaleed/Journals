package com.ezz.data.datasource;

import com.ezz.data.mapper.DataStatusMapper;
import com.ezz.data.remote.client.NewsAPI;
import com.ezz.data.remote.model.NewsRemote;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import io.reactivex.Scheduler;

import static com.ezz.data.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.data.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class SearchDataSourceFactory extends DataSource.Factory<Integer, NewsRemote> {

	private Scheduler subscribeOn;
	private Scheduler observeOn;
	private NewsAPI newsAPI;
	private DataStatusMapper dataStatusMapper;
	private String searchQuery;

	private SearchDataSource dataSource;

	@Inject
	public SearchDataSourceFactory(@Named(IO_SCHEDULER) Scheduler subscribeOn, @Named(MAIN_THREAD_SCHEDULER) Scheduler observeOn, NewsAPI newsAPI, DataStatusMapper dataStatusMapper, String searchQuery) {
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
		this.newsAPI = newsAPI;
		this.dataStatusMapper = dataStatusMapper;
		this.searchQuery = searchQuery;
	}

	@NonNull
	@Override
	public DataSource<Integer, NewsRemote> create() {
		this.dataSource = new SearchDataSource(subscribeOn, observeOn, newsAPI, searchQuery, dataStatusMapper);
		return dataSource;
	}

	public void dispose(){
		dataSource.dispose();
	}


	public void updateQuery(String searchQuery){
		this.searchQuery = searchQuery;
		dataSource.dispose();
		dataSource.invalidate();
	}

}
