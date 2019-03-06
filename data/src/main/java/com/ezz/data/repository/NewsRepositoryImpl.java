package com.ezz.data.repository;

import com.ezz.data.local.dao.NewsDao;
import com.ezz.data.local.model.NewsLocal;
import com.ezz.data.mapper.NewsMapper;
import com.ezz.data.remote.client.NewsAPI;
import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.data.remote.model.NewsResponse;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.repository.NewsRepository;
import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.resource.Resource;

import java.util.List;

import javax.inject.Inject;

import androidx.paging.DataSource;
import io.reactivex.Observable;

/*
  Created by Ezz Waleed on 06,March,2019
 */

/**
 * implementation of {@link NewsRepository} interface
 */
public class NewsRepositoryImpl implements NewsRepository {

	private NewsAPI newsAPI;
	private NewsDao newsDao;
	private NewsMapper newsMapper;

	@Inject
	public NewsRepositoryImpl(NewsAPI newsAPI, NewsDao newsDao, NewsMapper newsMapper) {
		this.newsAPI = newsAPI;
		this.newsDao = newsDao;
		this.newsMapper = newsMapper;
	}

	@Override
	public Observable<DataStatus> loadNews(int pageNumber) {
		return newsAPI.requestTopHeadlines(pageNumber).map((NewsResponse newsResponse) ->{
			DataStatus newsStatus = newsMapper.mapToDataStatus(newsResponse, pageNumber);
			insertNews(newsMapper.mapRemoteListToDomain(newsResponse.getNewsRemotes()), pageNumber == 1, newsStatus == DataStatus.HAS_LOADED_ALL_ITEMS);
			return newsStatus;
		}).toObservable();
	}

	@Override
	public DataSource.Factory<Integer, NewsDomain> getNewsDataSourceFactory() {
		return newsDao.newsByDate().map((NewsLocal newsLocal) -> newsMapper.mapToDomain(newsLocal));
	}

	@Override
	public Observable<Resource<List<NewsDomain>>> searchNews(String query, Integer pageNumber) {
		return newsAPI.searchForNews(query, pageNumber).map((NewsResponse newsResponse)
		-> newsMapper.mapToNewsDomainResource(newsResponse, pageNumber)).toObservable();
	}

	@Override
	public void insertNews(List<NewsDomain> newsDomainList, boolean isFirstPage, boolean hasLoadedAllItems) {
		newsDao.updateNews(newsMapper.mapDomainListToLocal(newsDomainList), isFirstPage, hasLoadedAllItems);
	}
}
