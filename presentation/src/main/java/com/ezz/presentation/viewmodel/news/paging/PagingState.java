package com.ezz.presentation.viewmodel.news.paging;

import javax.inject.Inject;

/**
 * Created by Ezz Waleed on 09,March,2019
 */

/**
 * Responsible to keep paging state with respect to android life cycle.
 */
public class PagingState {

	@Inject
	public PagingState() {
	}

	private int pageNumber = 1;

	private boolean loading = false;
	private boolean loadedAllItems = false;

	public int getPageNumber() {
		return pageNumber;
	}

	public boolean isLoading() {
		return loading;
	}

	public boolean isLoadedAllItems() {
		return loadedAllItems;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	public void setLoadedAllItems(boolean loadedAllItems) {
		this.loadedAllItems = loadedAllItems;
	}

	public void incrementPage(){
		pageNumber++;
	}
}
