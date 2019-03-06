package com.ezz.domain.repository;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.DataStatus;
import com.ezz.domain.resource.Resource;

import java.util.List;

import androidx.paging.PagedList;
import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface NewsRepository {

	/**
	 * loads {@link NewsDomain} list according to the pageNumber from repo
	 *
	 * @param pageNumber requested page number
	 * @return the requested {@link NewsDomain} list
	 */
	Observable<DataStatus> loadNews(int pageNumber);

	/**
	 * retrieves {@link NewsDomain} as a stream of observable pages
	 * @return the requested {@link NewsDomain} pagedList stream
	 */
	Observable<PagedList<NewsDomain>> getNewsPagedList();

	/**
	 * retrieves {@link NewsDomain} list associated with its network status as stream of Observable
	 * according to the requested search query
	 * @param query the requested search query
	 * @return list of {@link NewsDomain} list associated with its network status as stream of Observable
	 */
	Observable<Resource<List<NewsDomain>>> searchNews(String query, Integer pageNumber);

	/**
	 * insert list of {@link NewsDomain} into local db
	 * @param newsDomainList list required to insert
	 * @param isFirstPage specify whether the inserted list page is the first page or not
	 * @param hasLoadedAllItems specify whether the inserted list is the last page or not
	 */
	void insertNews(List<NewsDomain> newsDomainList, boolean isFirstPage, boolean hasLoadedAllItems);
}
