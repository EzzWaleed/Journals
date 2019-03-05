package com.ezz.domain.repository;


import com.ezz.domain.entity.NewsDomain;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Ezz Waleed on 04,March,2019
 */
public interface NewsRepository {

    Observable<NewsDomain> getNews(int pageNumber);

    Observable<NewsDomain> searchNews(String query);

    void insertNews(List<NewsDomain> newsDomainList);

}
