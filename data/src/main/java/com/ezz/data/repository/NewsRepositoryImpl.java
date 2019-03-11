package com.ezz.data.repository;

import com.ezz.data.datasource.SearchDataSourceFactory;
import com.ezz.data.local.dao.NewsDao;
import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.mapper.DataStatusMapper;
import com.ezz.data.mapper.DomainMapper;
import com.ezz.data.mapper.LocalMapper;
import com.ezz.data.remote.client.NewsAPI;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.repository.NewsRepository;
import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.resource.Resource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.paging.DataSource;
import io.reactivex.Observable;
import io.reactivex.Scheduler;

import static com.ezz.data.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.data.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/*
  Created by Ezz Waleed on 06,March,2019
 */

/**
 * implementation of {@link NewsRepository} interface
 */
public class NewsRepositoryImpl implements NewsRepository {

	private NewsAPI newsAPI;
	private NewsDao newsDao;
	private DomainMapper domainMapper;
	private LocalMapper localMapper;
	private DataStatusMapper dataStatusMapper;
	private SearchDataSourceFactory searchDataSourceFactory;
	private Scheduler subscribeOn;
	private Scheduler observeOn;

	@Inject
	NewsRepositoryImpl(NewsAPI newsAPI, NewsDao newsDao, DomainMapper domainMapper, LocalMapper localMapper, DataStatusMapper dataStatusMapper, @Named(IO_SCHEDULER) Scheduler subscribeOn, @Named(MAIN_THREAD_SCHEDULER) Scheduler observeOn) {
		this.newsAPI = newsAPI;
		this.newsDao = newsDao;
		this.domainMapper = domainMapper;
		this.localMapper = localMapper;
		this.dataStatusMapper = dataStatusMapper;
		this.subscribeOn = subscribeOn;
		this.observeOn = observeOn;
	}

	@Override
	public Observable<DataStatus> loadNews(int pageNumber) {
		return newsAPI.requestTopHeadlines(pageNumber, SettingsAPI.getCountryISO2()).map((NewsResponse newsResponse) ->{
			DataStatus newsStatus = dataStatusMapper.mapNewsResponseToDataStatus(newsResponse, pageNumber);
			insertNews(domainMapper.mapRemoteListToDomain(newsResponse.getNewsRemotes()), pageNumber == 1, newsStatus == DataStatus.HAS_LOADED_ALL_ITEMS);
			return newsStatus;
		}).toObservable();
	}

	@Override
	public DataSource.Factory<Integer, NewsDomain> getNewsDataSourceFactory() {
		return newsDao.newsByDate().map((NewsLocal newsLocal) -> domainMapper.mapLocalToDomain(newsLocal));
	}

	@Override
	public DataSource.Factory<Integer, NewsDomain> getSearchNewsDataSource(String query) {
		searchDataSourceFactory = new SearchDataSourceFactory(subscribeOn, observeOn, newsAPI, dataStatusMapper, query);
		return searchDataSourceFactory.map(input -> domainMapper.mapRemoteToDomain(input));
	}

	@Override
	public void updateSearchQuery(String query) {
		searchDataSourceFactory.updateQuery(query);
	}

	@Override
	public void insertNews(List<NewsDomain> newsDomainList, boolean isFirstPage, boolean hasLoadedAllItems) {
		newsDao.updateNews(localMapper.mapDomainListToLocal(newsDomainList), isFirstPage, hasLoadedAllItems);
	}
}
