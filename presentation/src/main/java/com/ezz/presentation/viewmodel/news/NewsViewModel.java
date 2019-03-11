package com.ezz.presentation.viewmodel.news;

import com.ezz.domain.resource.DataStatus;

import com.ezz.domain.usecase.GetNewsUseCase;

import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;
import com.ezz.presentation.viewmodel.news.paging.PagingState;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import io.reactivex.Scheduler;

import static com.ezz.data.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.data.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
public class NewsViewModel extends BaseViewModel {

	private GetNewsUseCase<NewsUI> newsUseCase;
	private PagingState pagingState;

	private MutableLiveData<PagedList<NewsUI>> newsPagedListLiveData = new MutableLiveData<>();
	private MutableLiveData<DataStatus> loadNewsStats = new MutableLiveData<>();

	private MutableLiveData<DataStatus> pagingDataStatus = new MutableLiveData<>();

	@Inject
	NewsViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, GetNewsUseCase<NewsUI> newsUsecase, PagingState pagingState) {
		super(subscribeOn, observeOn);
		this.newsUseCase = newsUsecase;
		this.pagingState = pagingState;
	}

	/**
	 * Creates a paged list of {@link NewsUI} as a stream of LiveData,
	 * and assign it to newsPagedListLiveData variable if it not already assigned.
	 */
	public void createNewsPagedList() {
		execute(
		disposable -> pagingDataStatus.postValue(DataStatus.LOADING),
		newsUIPagedList -> {
			newsPagedListLiveData.postValue(newsUIPagedList);
			pagingDataStatus.setValue(DataStatus.SUCCESS);
		},
		throwable -> pagingDataStatus.postValue(DataStatus.ERROR),
		newsUseCase.getNewsPagedList()
		);
	}

	/**
	 * loads news data according to the requested page number.
	 *
	 * @param pageNumber requested page number to be loaded
	 */
	public void loadNews(int pageNumber) {
		execute(loading -> loadNewsStats.postValue(DataStatus.LOADING),
		dataStatus -> loadNewsStats.postValue(dataStatus),
		throwable -> loadNewsStats.postValue(DataStatus.ERROR),
		newsUseCase.loadNews(pageNumber));
	}

	public LiveData<PagedList<NewsUI>> getNewsPagedListLiveData() {
		return newsPagedListLiveData;
	}

	public LiveData<DataStatus> getLoadNewsStats() {
		return loadNewsStats;
	}

	public MutableLiveData<DataStatus> getPagingDataStatus() {
		return pagingDataStatus;
	}

	public PagingState getPagingState() {
		return pagingState;
	}
}
