package com.ezz.presentation.viewmodel.search.datasource;

import com.ezz.domain.usecase.SearchNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;

import androidx.annotation.NonNull;
import androidx.paging.DataSource;
import io.reactivex.Scheduler;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
public class SearchDataSourceFactory extends DataSource.Factory<Integer, NewsUI> {

	private SearchDataSource searchDataSource;

	public SearchDataSourceFactory(Scheduler subscribeOn, Scheduler observeOn, SearchNewsUsecase searchNewsUsecase, NewsMapper newsMapper, String searchQuery) {
		this.searchDataSource = new SearchDataSource(subscribeOn, observeOn, searchNewsUsecase, newsMapper, searchQuery);
	}

	@NonNull
	@Override
	public DataSource<Integer, NewsUI> create() {
		return searchDataSource;
	}

	public void dispose(){
		searchDataSource.dispose();
	}

}
