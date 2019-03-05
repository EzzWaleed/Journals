package com.ezz.domain.usecase;

import com.ezz.domain.repository.NewsRepository;

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

    public Observable searchNews(String query){
        return newsRepository.searchNews(query);
    }
}
