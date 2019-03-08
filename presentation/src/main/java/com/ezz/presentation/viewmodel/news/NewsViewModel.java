package com.ezz.presentation.viewmodel.news;


import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.DataStatus;

import com.ezz.domain.usecase.GetNewsUsecase;

import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;

import javax.inject.Inject;
import javax.inject.Named;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.Scheduler;

import static com.ezz.presentation.di.SchedulersModule.IO_SCHEDULER;
import static com.ezz.presentation.di.SchedulersModule.MAIN_THREAD_SCHEDULER;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
public class NewsViewModel extends BaseViewModel {

	private GetNewsUsecase newsUsecase;
	private NewsMapper newsMapper;

	public LiveData<PagedList<NewsUI>> newsPagedListLiveData;

	public MutableLiveData<DataStatus> loadNewsStats = new MutableLiveData<>();

	@Inject
	public NewsViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, GetNewsUsecase newsUsecase, NewsMapper newsMapper) {
		super(subscribeOn, observeOn);
		this.newsUsecase = newsUsecase;
		this.newsMapper = newsMapper;
		newsPagedListLiveData = createNewsPagedList();
	}

	/**
	 * Creates a paged list of {@link NewsUI} as a stream of LiveData.
	 * @return the created instance of pagedList as a stream of LiveData.
	 */
	private LiveData<PagedList<NewsUI>> createNewsPagedList(){
		DataSource.Factory<Integer, NewsUI> dataSourceFactory =
		newsUsecase.getNewsPagedList().map((NewsDomain newsDomain) -> newsMapper.mapToUI(newsDomain));

		PagedList.Config config = new PagedList.Config.Builder()
		.setPageSize(SettingsAPI.getNumberOfItemsPerPage()).build();

		return new LivePagedListBuilder<>(dataSourceFactory, config).build();
	}

	public void loadNews(int pageNumber){
		execute(loading -> loadNewsStats.postValue(DataStatus.LOADING),
		dataStatus -> loadNewsStats.postValue(dataStatus),
		throwable -> loadNewsStats.postValue(DataStatus.ERROR),
		newsUsecase.loadNews(pageNumber));
	}

}
