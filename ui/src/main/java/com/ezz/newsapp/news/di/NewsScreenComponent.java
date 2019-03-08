package com.ezz.newsapp.news.di;

import com.ezz.newsapp.news.NewsActivity;
import com.ezz.newsapp.paging.PagingManger;
import com.ezz.presentation.di.PresentationComponent;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Ezz Waleed on 08,March,2019
 */
@NewsScreenScope
@Component(dependencies = {PresentationComponent.class})
public interface NewsScreenComponent {

	void inject(NewsActivity newsActivity);

	@Component.Builder
	interface Builder{

		@BindsInstance
		Builder loadMoreListener(PagingManger.LoadMoreListener loadMoreListener);

		Builder PresentationComponent(PresentationComponent presentationComponent);

		NewsScreenComponent build();

	}

}
