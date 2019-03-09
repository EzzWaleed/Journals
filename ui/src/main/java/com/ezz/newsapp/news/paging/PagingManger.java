package com.ezz.newsapp.news.paging;

import com.ezz.presentation.viewmodel.news.paging.PagingKeeper;
import com.paginate.Paginate;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by Ezz Waleed on 08,March,2019
 */

/**
 * Manage Paginate callbacks and states
 */
public class PagingManger implements Paginate.Callbacks {

	private PagingKeeper pagingKeeper;

	private LoadMoreListener loadMoreListener;


	@Inject
	public PagingManger(@NonNull LoadMoreListener loadMoreListener) {
		this.loadMoreListener = loadMoreListener;
	}

	@Override
	public void onLoadMore() {
		loadMoreListener.onLoadMore();
	}

	@Override
	public boolean isLoading() {
		return pagingKeeper.isLoading();
	}

	@Override
	public boolean hasLoadedAllItems() {
		return pagingKeeper.isLoadedAllItems();
	}

	public void setLoading(boolean loading) {
		this.pagingKeeper.setLoading(loading);
	}

	public void setLoadedAllItems(boolean loadedAllItems) {
		this.pagingKeeper.setLoadedAllItems(loadedAllItems);
	}

	public interface LoadMoreListener{
		void onLoadMore();
	}

	public int getPageNumber() {
		return pagingKeeper.getPageNumber();
	}

	public void incrementPageNumber(){
		pagingKeeper.incrementPage();
	}

	public void setPagingKeeper(@Nullable PagingKeeper pagingKeeper) {
		this.pagingKeeper = pagingKeeper;
	}
}
