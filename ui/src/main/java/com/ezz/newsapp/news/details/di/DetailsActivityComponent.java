package com.ezz.newsapp.news.details.di;

import android.content.Context;

import com.ezz.newsapp.di.SharedPrefranceModule;
import com.ezz.newsapp.news.details.DetailsActivity;

import dagger.BindsInstance;
import dagger.Component;

/**
 * Created by Ezz Waleed on 09,March,2019
 */
@Component(modules = SharedPrefranceModule.class)
public interface DetailsActivityComponent {

	void inject(DetailsActivity detailsActivity);

	@Component.Builder
	interface Builder{

		@BindsInstance
		Builder bindContext(Context context);

		DetailsActivityComponent build();
	}

}
