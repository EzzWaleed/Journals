package com.ezz.domain.usecase;


import com.ezz.domain.entity.News;
import com.ezz.domain.repository.NewsRepository;

import javax.inject.Inject;

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

    public Observable<News> getNews(int pageNumber){
        return newsRepository.getNews(pageNumber);
    }

}
