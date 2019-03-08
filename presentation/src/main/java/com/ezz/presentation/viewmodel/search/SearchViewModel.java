package com.ezz.presentation.viewmodel.search;

import com.ezz.domain.resource.Resource;
import com.ezz.domain.usecase.SearchNewsUsecase;
import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Scheduler;

import static com.ezz.presentation.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.presentation.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
public class SearchViewModel extends BaseViewModel {

	SearchNewsUsecase searchNewsUsecase;

	NewsMapper newsMapper;

	public MutableLiveData<Resource<List<NewsUI>>> searchedNewsLiveData = new MutableLiveData<>();

	@Inject
	public SearchViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, SearchNewsUsecase searchNewsUsecase, NewsMapper newsMapper) {
		super(subscribeOn, observeOn);
		this.searchNewsUsecase = searchNewsUsecase;
		this.newsMapper = newsMapper;
	}

	/**
	 * Send search request for a news.
	 * @param query Search query.
	 * @param pageNumber requested page number.
	 */
	public void searchNews(String query, int pageNumber){
		execute(
		disposable -> searchedNewsLiveData.postValue(Resource.loading()),
		newsDomainListResource -> searchedNewsLiveData.setValue(newsMapper.mapToUIResourceList(newsDomainListResource)),
		throwable -> searchedNewsLiveData.setValue(Resource.error(throwable.getMessage())),
		searchNewsUsecase.searchNews(query, pageNumber)
		);
	}
}
