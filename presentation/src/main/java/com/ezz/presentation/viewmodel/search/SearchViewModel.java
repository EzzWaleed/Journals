package com.ezz.presentation.viewmodel.search;

import com.ezz.domain.usecase.SearchNewsUseCase;
import com.ezz.presentation.model.NewsUI;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class SearchViewModel extends ViewModel {

	private String searchQueryState;

	private SearchNewsUseCase<NewsUI> searchNewsUseCase;

	//Exposed liveData
	private LiveData<PagedList<NewsUI>> pagedListLiveData;

	@Inject
	SearchViewModel(SearchNewsUseCase<NewsUI> searchNewsUseCase) {
		this.searchNewsUseCase = searchNewsUseCase;
	}

	public void searchFor(String query) {
		searchQueryState = query;

		if (pagedListLiveData == null)
			pagedListLiveData = searchNewsUseCase.getSearchPagedList(query);
		else
			searchNewsUseCase.updateSearchQuery(query);
	}

	public LiveData<PagedList<NewsUI>> getNewsLiveData() {
		return pagedListLiveData;
	}

	public String getSearchQueryState() {
		return searchQueryState;
	}

	@Override
	protected void onCleared() {
		super.onCleared();
	}

}
