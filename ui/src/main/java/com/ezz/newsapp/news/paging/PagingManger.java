package com.ezz.newsapp.news.paging;

import com.ezz.presentation.viewmodel.news.paging.PagingState;
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

	private PagingState pagingState;

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
		return pagingState.isLoading();
	}

	@Override
	public boolean hasLoadedAllItems() {
		return pagingState.isLoadedAllItems();
	}

	public void setLoading(boolean loading) {
		this.pagingState.setLoading(loading);
	}

	public void setLoadedAllItems(boolean loadedAllItems) {
		this.pagingState.setLoadedAllItems(loadedAllItems);
	}

	public interface LoadMoreListener{
		void onLoadMore();
	}

	public int getPageNumber() {
		return pagingState.getPageNumber();
	}

	public void incrementPageNumber(){
		pagingState.incrementPage();
	}

	public void setPagingState(@Nullable PagingState pagingState) {
		this.pagingState = pagingState;
	}
}
