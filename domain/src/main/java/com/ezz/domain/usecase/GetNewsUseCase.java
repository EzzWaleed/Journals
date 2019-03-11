package com.ezz.domain.usecase;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.mapper.Mapper;
import com.ezz.domain.repository.NewsRepository;
import com.ezz.domain.resource.DataStatus;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class GetNewsUseCase<T> {

	private NewsRepository newsRepository;
	private Mapper<DataSource.Factory<Integer, NewsDomain>, DataSource.Factory<Integer, T>> mapper;

	@Inject
	public GetNewsUseCase(NewsRepository newsRepository, Mapper<DataSource.Factory<Integer, NewsDomain>, DataSource.Factory<Integer, T>> mapper) {
		this.newsRepository = newsRepository;
		this.mapper = mapper;
	}

	/**
	 * request to load a new batch of {@link NewsDomain} according to the required pageNumber
	 *
	 * @param pageNumber requested pageNumber
	 * @return requested data status
	 */
	public Observable<DataStatus> loadNews(int pageNumber) {
		return newsRepository.loadNews(pageNumber);
	}
	/**
	 * retrieves {@link NewsDomain} data source factory.
	 *
	 * @return the requested {@link NewsDomain} data source factory.
	 */
	// TODO: 3/7/19 change return type to PagedList when google adds PagedList transformation capability.
	public LiveData<PagedList<T>> getNewsPagedList() {
		DataSource.Factory<Integer, T> dataSourceFactory = mapper.map(newsRepository.getNewsDataSourceFactory());
		return new LivePagedListBuilder<>(dataSourceFactory, 10).build();
	}
}
