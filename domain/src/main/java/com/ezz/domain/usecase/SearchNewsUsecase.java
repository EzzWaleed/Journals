package com.ezz.domain.usecase;

import com.ezz.domain.entity.NewsDomain;
import com.ezz.domain.repository.NewsRepository;
import com.ezz.domain.resource.Resource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public class SearchNewsUsecase {

    private NewsRepository newsRepository;

    @Inject
    public SearchNewsUsecase(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * retrieves {@link NewsDomain} list associated with its network status as stream of Observable
     * according to the requested search query and page number
     * @param query the requested search query
     * @param pageNumber requested page number
     * @return list of {@link NewsDomain} list associated with its network status as stream of Observable
     */
    public Observable<Resource<List<NewsDomain>>> searchNews(String query, Integer pageNumber){
        return newsRepository.searchNews(query, pageNumber);
    }
}
