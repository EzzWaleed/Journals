package com.ezz.domain.repository;


import com.ezz.domain.entity.News;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface NewsRepository {

    Observable<News> getNews(int pageNumber);

    Observable<News> searchNews(String query);

    void insertNews(List<News> newsList);

}
