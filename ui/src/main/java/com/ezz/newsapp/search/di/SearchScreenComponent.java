package com.ezz.newsapp.search.di;

import com.ezz.newsapp.search.SearchActivity;
import com.ezz.presentation.di.PresentationComponent;

import dagger.Component;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
@Component(dependencies = PresentationComponent.class)
@SearchScreenScope
public interface SearchScreenComponent {
	void inject(SearchActivity searchActivity);
}
