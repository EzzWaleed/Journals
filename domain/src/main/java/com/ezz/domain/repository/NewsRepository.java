package com.ezz.domain.repository;


import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.entity.News;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface NewsRepository {

	/**
	 * retrieves {@link News} list according to the pageNumber from repo
	 *
	 * @param pageNumber requested page number
	 * @return the requested {@link News} list
	 */
	Observable<News> getNews(int pageNumber);

	/**
	 * retrieves {@link News} list according to the search query from repo
	 * @param query  search query
	 * @return the requested {@link News} list
	 */
	Observable<News> searchNews(String query);

	/**
	 * insert list of {@link News} into local db
	 * @param newsList list required to insert
	 * @param isFirstPage
	 * @param hasLoadedAllItems
	 */
	void insertNews(List<News> newsList, boolean isFirstPage, boolean hasLoadedAllItems);
}
