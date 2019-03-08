package com.ezz.newsapp.paging;

import com.paginate.Paginate;

import javax.inject.Inject;

import androidx.annotation.NonNull;

/**
 * Created by Ezz Waleed on 08,March,2019
 */

/**
 * Manage Paginate callbacks and states
 */
public class PagingCallbacksManger implements Paginate.Callbacks {

	private boolean loading = false;
	private boolean loadedAllItems = false;
	private LoadMoreListener loadMoreListener;

	@Inject
	public PagingCallbacksManger(@NonNull LoadMoreListener loadMoreListener) {
		this.loadMoreListener = loadMoreListener;
	}

	@Override
	public void onLoadMore() {
		loadMoreListener.onLoadMore();
	}

	@Override
	public boolean isLoading() {
		return loading;
	}

	@Override
	public boolean hasLoadedAllItems() {
		return loadedAllItems;
	}

	public void setLoading(boolean loading) {
		this.loading = loading;
	}

	public void setLoadedAllItems(boolean loadedAllItems) {
		this.loadedAllItems = loadedAllItems;
	}

	public interface LoadMoreListener{
		void onLoadMore();
	}
}
