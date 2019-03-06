package com.ezz.data.repository;

import com.ezz.data.local.dao.NewsDao;
import com.ezz.data.mapper.NewsMapper;
import com.ezz.data.remote.client.NewsAPI;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.repository.NewsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 06,March,2019
 */

/**
 * Implementation of {@link NewsRepository} interface.
 */
public class NewsRepositoryImpl implements NewsRepository {

	private NewsDao newsDao;
	private NewsAPI newsAPI;

	@Inject
	public NewsRepositoryImpl(NewsDao newsDao, NewsAPI newsAPI) {
		this.newsDao = newsDao;
		this.newsAPI = newsAPI;
	}

	@Override
	public Observable<NewsDomain> getNews(int pageNumber) {
		newsAPI.requestTopHeadlines(pageNumber).;

	}

	@Override
	public Observable<NewsDomain> searchNews(String query) {
		return null;
	}

	@Override
	public void insertNews(List<NewsDomain> newsDomainList, boolean isFirstPage, boolean hasLoadedAllItems) {

	}
}
