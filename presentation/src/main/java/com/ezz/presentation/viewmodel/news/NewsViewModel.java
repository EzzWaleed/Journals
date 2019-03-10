package com.ezz.presentation.viewmodel.news;

import com.ezz.data.remote.client.SettingsAPI;
import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.resource.DataStatus;

import com.ezz.domain.usecase.GetNewsUsecase;

import com.ezz.presentation.mapper.NewsMapper;
import com.ezz.presentation.model.NewsUI;
import com.ezz.presentation.viewmodel.BaseViewModel;
import com.ezz.presentation.viewmodel.news.paging.PagingKeeper;

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
	private PagingKeeper pagingKeeper;

	private LiveData<PagedList<NewsUI>> newsPagedListLiveData;
	private MutableLiveData<DataStatus> loadNewsStats = new MutableLiveData<>();

	@Inject
	public NewsViewModel(@Named(value = IO_SCHEDULER) Scheduler subscribeOn, @Named(value = MAIN_THREAD_SCHEDULER) Scheduler observeOn, GetNewsUsecase newsUsecase, NewsMapper newsMapper, PagingKeeper pagingKeeper) {
		super(subscribeOn, observeOn);
		this.newsUsecase = newsUsecase;
		this.newsMapper = newsMapper;
		this.pagingKeeper = pagingKeeper;
	}

	/**
	 * Creates a paged list of {@link NewsUI} as a stream of LiveData,
	 * and assign it to newsPagedListLiveData variable if it not already assigned.
	 */
	public void createNewsPagedList() {
		if (newsPagedListLiveData == null) {
			DataSource.Factory<Integer, NewsUI> dataSourceFactory =
			newsUsecase.getNewsPagedList().map((NewsDomain newsDomain) -> newsMapper.mapToUI(newsDomain));

			PagedList.Config config = new PagedList.Config.Builder()
			.setPageSize(SettingsAPI.getNumberOfItemsPerPage()).build();

			newsPagedListLiveData = new LivePagedListBuilder<>(dataSourceFactory, config).build();
		}
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
		newsUsecase.loadNews(pageNumber));
	}

	public LiveData<PagedList<NewsUI>> getNewsPagedListLiveData() {
		return newsPagedListLiveData;
	}

	public LiveData<DataStatus> getLoadNewsStats() {
		return loadNewsStats;
	}

	public PagingKeeper getPagingKeeper() {
		return pagingKeeper;
	}
}
