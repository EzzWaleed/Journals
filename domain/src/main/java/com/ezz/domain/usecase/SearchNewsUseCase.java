package com.ezz.domain.usecase;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.mapper.Mapper;
import com.ezz.domain.repository.NewsRepository;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class SearchNewsUseCase<T> {

    private NewsRepository newsRepository;
    private Mapper<DataSource.Factory<Integer, NewsDomain>, DataSource.Factory<Integer, T>> mapper;

    @Inject
    public SearchNewsUseCase(NewsRepository newsRepository, Mapper<DataSource.Factory<Integer, NewsDomain>, DataSource.Factory<Integer, T>> mapper) {
        this.newsRepository = newsRepository;
        this.mapper = mapper;
    }

    /**
     * retrieves {@link NewsDomain} paged list as stream of liveData according to the requested search query,
     * @param query the requested search query
     * @return requested pagedList.
     */
    public LiveData<PagedList<T>> getSearchPagedList(String query){
        DataSource.Factory<Integer, T> dataSourceFactory = mapper.map(newsRepository.getSearchNewsDataSource(query));
        return new LivePagedListBuilder<>(dataSourceFactory, 10).build();
    }


    /**
     * updates {@link DataSource.Factory} search query.
     * @param query new search query
     */
    public void updateSearchQuery(String query){
        newsRepository.updateSearchQuery(query);
    }

}
