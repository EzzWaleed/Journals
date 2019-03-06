package com.ezz.domain.usecase;


import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.repository.NewsRepository;
import com.ezz.domain.resource.DataStatus;

import javax.inject.Inject;

import androidx.paging.DataSource;
import androidx.paging.PagedList;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class GetNewsUsecase {

    private NewsRepository newsRepository;

    @Inject
    public GetNewsUsecase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

	/**
	 * request to load a new batch of {@link NewsDomain} according to the required pageNumber
	 * @param pageNumber requested pageNumber
	 * @return requested data status
	 */
	public Observable<DataStatus> loadNews(int pageNumber){
        return newsRepository.loadNews(pageNumber);
    }

	/**
	 * retrieves {@link NewsDomain} data source factory.
	 * @return the requested {@link NewsDomain} data source factory.
	 */
	// TODO: 3/7/19 change return type to PagedList when google adds PagedList transformation capability.
	public DataSource.Factory<Integer, NewsDomain> getNewsPagedList(){
		return newsRepository.getNewsDataSourceFactory();
	}

}
