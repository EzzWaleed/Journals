package com.ezz.domain.repository;

import com.ezz.domain.entity.NewsDomain;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface NewsRepository {

	/**
	 * retrieves {@link NewsDomain} list according to the pageNumber from repo
	 *
	 * @param pageNumber requested page number
	 * @return the requested {@link NewsDomain} list
	 */
	Observable<NewsDomain> getNews(int pageNumber);

	/**
	 * retrieves {@link NewsDomain} list according to the search query from repo
	 * @param query  search query
	 * @return the requested {@link NewsDomain} list
	 */
	Observable<NewsDomain> searchNews(String query);

	/**
	 * insert list of {@link NewsDomain} into local db
	 * @param newsDomainList list required to insert
	 * @param isFirstPage
	 * @param hasLoadedAllItems
	 */
	void insertNews(List<NewsDomain> newsDomainList, boolean isFirstPage, boolean hasLoadedAllItems);
}
