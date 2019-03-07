package com.ezz.presentation.viewmodel.news;


import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.DataStatus;

import com.ezz.domain.usecase.GetNewsUsecase;

import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import io.reactivex.Scheduler;

/**
 * Created by Ezz Waleed on 07,March,2019
 */
public class NewsViewModel extends BaseViewModel {

	private GetNewsUsecase newsUsecase;
	private NewsMapper newsMapper;

	public LiveData<PagedList<NewsUI>> newsPagedListLiveData;

	public MutableLiveData<DataStatus> loadNewsStats = new MutableLiveData<>();

	@Inject
	public NewsViewModel(Scheduler subscribeOn, Scheduler observeOn, GetNewsUsecase newsUsecase) {
		super(subscribeOn, observeOn);
		this.newsUsecase = newsUsecase;
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
		execute(loading -> loadNewsStats.setValue(DataStatus.LOADING),
		dataStatus -> {loadNewsStats.setValue(dataStatus);},
		throwable -> loadNewsStats.setValue(DataStatus.ERROR),
		newsUsecase.loadNews(pageNumber));
	}

}
